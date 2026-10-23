package task.battilana.com.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import task.battilana.com.entity.UsuariosEntity;
import task.battilana.com.repository.UsuarioRepository;

@Service
public class CustomDetailService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomDetailService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        UsuariosEntity usuariosEntity = this.usuarioRepository.findByCorreo(correo);

        UserDetails userDetails = User.builder()
                .username(usuariosEntity.getCorreo())
                .password(usuariosEntity.getPassword())
                .roles(usuariosEntity.getRoles().name())
                .build();

        return userDetails;
    }
}
