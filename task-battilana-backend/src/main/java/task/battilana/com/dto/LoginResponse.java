package task.battilana.com.dto;

public record LoginResponse (
        Long idUsuarios,
        String token,
        String rol,
        String statusResponse
){
}
