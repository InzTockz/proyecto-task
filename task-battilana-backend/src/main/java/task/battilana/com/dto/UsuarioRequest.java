package task.battilana.com.dto;

import task.battilana.com.entity.Roles;

public record UsuarioRequest(
        String nombres,
        String apellidos,
        String correo,
        String password,
        Roles roles
) {
}
