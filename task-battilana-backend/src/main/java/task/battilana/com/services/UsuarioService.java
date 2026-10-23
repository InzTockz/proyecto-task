package task.battilana.com.services;

import task.battilana.com.dto.LoginRequest;
import task.battilana.com.dto.LoginResponse;
import task.battilana.com.dto.UsuarioRequest;
import task.battilana.com.dto.UsuarioResponse;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResponse> listado();
    UsuarioResponse registrar (UsuarioRequest usuarioRequest);
    UsuarioResponse buscar(Long idUsuario);
    UsuarioResponse actualizar(Long idUsuario, UsuarioRequest usuarioRequest);
    void eliminar(Long idUsuario);

    LoginResponse login(LoginRequest loginRequest);
}
