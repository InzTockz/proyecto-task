package task.battilana.com.dto;

public record TareaRequest(
        String nombreTarea,
        String descripcion,
        String fechaInicio,
        String fechaFin,
        Long idUsuariosEntity,
        Long idCarpeta
) {
}
