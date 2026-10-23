package task.battilana.com.services.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import task.battilana.com.dto.LoginRequest;
import task.battilana.com.dto.LoginResponse;
import task.battilana.com.dto.UsuarioRequest;
import task.battilana.com.dto.UsuarioResponse;
import task.battilana.com.entity.UsuariosEntity;
import task.battilana.com.jwt.JwtGenerator;
import task.battilana.com.mapper.UsuarioMapper;
import task.battilana.com.repository.UsuarioRepository;
import task.battilana.com.services.UsuarioService;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioMapper usuarioMapper;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtGenerator jwtGenerator;
    private final AuthenticationManager authenticationManager;

    public UsuarioServiceImpl(UsuarioMapper usuarioMapper, UsuarioRepository usuarioRepository, BCryptPasswordEncoder encoder, JwtGenerator jwtGenerator, AuthenticationManager authenticationManager) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
        this.jwtGenerator = jwtGenerator;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public List<UsuarioResponse> listado() {
        return this.usuarioMapper.listadoUsuarioMapper(this.usuarioRepository.findByEstadoTrue());
    }

    @Override
    public UsuarioResponse registrar(UsuarioRequest usuarioRequest) {

        UsuariosEntity usuario = this.usuarioRepository.findByCorreo(usuarioRequest.correo());

        if (usuario != null) {
            return new UsuarioResponse(null, "", "", "", false,
                    "", "user_registered");
        } else {
            UsuariosEntity usuariosEntity = this.usuarioMapper.usuariosEntityMapper(usuarioRequest);
            usuariosEntity.setPassword(this.encoder.encode(usuariosEntity.getPassword()));
            return this.usuarioMapper.usuarioDtoMapper(this.usuarioRepository.save(usuariosEntity));
        }
    }

    @Override
    public UsuarioResponse buscar(Long idUsuario) {
        return this.usuarioMapper.usuarioDtoMapper(this.usuarioRepository.findById(idUsuario).get());
    }

    @Override
    public UsuarioResponse actualizar(Long idUsuario, UsuarioRequest usuarioRequest) {
        UsuariosEntity usuariosEntity = this.usuarioRepository.findById(idUsuario).get();

        usuariosEntity.setNombres(usuarioRequest.nombres());
        usuariosEntity.setApellidos(usuarioRequest.apellidos());
        usuariosEntity.setCorreo(usuarioRequest.correo());
        if (!usuarioRequest.password().equals("")) {
            usuariosEntity.setPassword(encoder.encode(usuarioRequest.password()));
        }
        usuariosEntity.setRoles(usuarioRequest.roles());

        return this.usuarioMapper.usuarioDtoMapper(this.usuarioRepository.save(usuariosEntity));
    }

    @Override
    public void eliminar(Long idUsuario) {
        UsuariosEntity usuariosEntity = this.usuarioRepository.findById(idUsuario).get();
        usuariosEntity.setEstado(false);
        this.usuarioRepository.save(usuariosEntity);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        UsuariosEntity usuariosEntity = this.usuarioRepository.findByCorreo(loginRequest.username());

        if (usuariosEntity != null) {
            boolean passwordIsTrue = this.encoder.matches(loginRequest.password(), usuariosEntity.getPassword());

            if (loginRequest.username().equals(usuariosEntity.getCorreo()) && passwordIsTrue) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String token = this.jwtGenerator.getToken(loginRequest.username());

                return new LoginResponse(
                        usuariosEntity.getIdUsuarios(),
                        token,
                        usuariosEntity.getRoles().name(),
                        "success"
                );
            } else {
                return new LoginResponse(
                        null,
                        "",
                        "",
                        "invalid_credentials"
                );
            }
        } else {
            return new LoginResponse(
                    null,
                    "",
                    "",
                    "invalid_credentials"
            );
        }
    }
}
