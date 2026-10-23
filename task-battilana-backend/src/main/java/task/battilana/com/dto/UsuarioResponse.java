package task.battilana.com.dto;

public record UsuarioResponse(
        Long idUsuarios,
        String nombres,
        String apellidos,
        String correo,
        Boolean estado,
        String roles,
        String registerStatus
) {
}
