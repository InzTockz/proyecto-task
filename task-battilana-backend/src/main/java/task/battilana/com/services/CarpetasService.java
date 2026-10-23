package task.battilana.com.services;

import task.battilana.com.dto.CarpetaRequest;
import task.battilana.com.dto.CarpetaResponse;

import java.time.LocalDate;
import java.util.List;

public interface CarpetasService {

    List<CarpetaResponse> listado();
    CarpetaResponse registrar(CarpetaRequest carpetaRequest);
    List<CarpetaResponse> buscarListaId(Long idUsuario);
    CarpetaResponse buscarId(Long idUsuario);
    List<CarpetaResponse> buscarCarpetaPorUsuarioFechaYEstado(Long idUsuario, LocalDate firstDate, LocalDate lastDate);
}
