package task.battilana.com.services.impl;

import org.springframework.stereotype.Service;
import task.battilana.com.dto.CarpetaRequest;
import task.battilana.com.dto.CarpetaResponse;
import task.battilana.com.mapper.CarpetaMapper;
import task.battilana.com.repository.CarpetasRepository;
import task.battilana.com.services.CarpetasService;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarpetasServiceImpl implements CarpetasService {

    private final CarpetasRepository carpetasRepository;
    private final CarpetaMapper carpetaMapper;

    public CarpetasServiceImpl(CarpetasRepository carpetasRepository, CarpetaMapper carpetaMapper) {
        this.carpetasRepository = carpetasRepository;
        this.carpetaMapper = carpetaMapper;
    }

    @Override
    public List<CarpetaResponse> listado() {
        return this.carpetaMapper.toListCarpetaResponse(this.carpetasRepository.findAll());
    }

    @Override
    public CarpetaResponse registrar(CarpetaRequest carpetaRequest) {
        return this.carpetaMapper.toCarpetaResponse(this.carpetasRepository.save(this.carpetaMapper.toCarpetaEntity(carpetaRequest)));
    }

    @Override
    public List<CarpetaResponse> buscarListaId(Long idUsuario) {
        return this.carpetaMapper.toListCarpetaResponse(this.carpetasRepository.findCarpetaByUsuario(idUsuario));
    }

    @Override
    public CarpetaResponse buscarId(Long idUsuario) {
        return this.carpetaMapper.toCarpetaResponse(this.carpetasRepository.findById(idUsuario).get());
    }

    @Override
    public List<CarpetaResponse> buscarCarpetaPorUsuarioFechaYEstado(Long idUsuario, LocalDate firstDate, LocalDate lastDate) {
        return this.carpetaMapper.toListCarpetaResponse(this.carpetasRepository.buscarCarpetaPorUsuarioFechaYEstado(firstDate, lastDate, idUsuario));
    }


}
