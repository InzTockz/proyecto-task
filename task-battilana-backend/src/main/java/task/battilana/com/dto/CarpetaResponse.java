package task.battilana.com.dto;

public record CarpetaResponse(
        Long idCarpeta,
        String nombreCarpeta,
        String fechaCreacion,
        String fechaModificacion,
        Long idUsuario
        //Integer contadorDeTareas
) {
}
