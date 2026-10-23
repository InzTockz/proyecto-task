package task.battilana.com.services;

import task.battilana.com.dto.TareaRequest;
import task.battilana.com.dto.TareaResponse;
import task.battilana.com.entity.EstadoEnum;

import java.util.List;

public interface TareasService {

    List<TareaResponse> listado();
    TareaResponse registrar (TareaRequest tareaDto);
    TareaResponse actualizar (Long id, TareaRequest tareaDto);
    TareaResponse buscar (Long id);
    void eliminar (Long id);

    // FUNCIONES EXTRAS
    Integer contadorPendientes();
    Integer contadorTerminados();
    Integer contadorTotales();
    void actualizarEstado (Long id);
    void agregarComentario(Long idTarea, String comentario);

    //LISTADO POR TIPO
    List<TareaResponse> listadoPorPendiente();
    List<TareaResponse> listadoPorTerminado();

    //LISTADO POR USUARIOS
    List<TareaResponse> listadoTareasPendientePorUsuario(Long idUsuario);
    List<TareaResponse> listadoTareasTerminadoPorUsuario(Long idUsuario);
    List<TareaResponse> listadoTareasTotalPorUsuario(Long idUsuario);

    //CONTADOR POR USUARIO
    Integer  contadorPendientesPorUsuario(Long idUsuario);
    Integer  contadorCompletadoPorUsuario(Long idUsuario);
    Integer  contadorTotalPorUsuario(Long idUsuario);

    //LISTAD TAREAS POR CARPETA
    List<TareaResponse> listadoTareasPorCarpeta(Long idCarpeta, EstadoEnum estado);
    Integer contadorPorCarpetasYEstado(Long idCarpeta, EstadoEnum estado);

    //LISTADO DE TAREAS POR USUARIO Y SIN CARPETA
    List<TareaResponse> listadoTareasPorUsuarioSinCarpeta(Long idUsuario);
    TareaResponse actualizarCarpetaPorTarea(Long idTarea, Long idCarpeta);
}
