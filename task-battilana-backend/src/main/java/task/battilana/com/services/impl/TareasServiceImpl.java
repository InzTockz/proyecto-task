package task.battilana.com.services.impl;

import org.springframework.stereotype.Service;
import task.battilana.com.dto.TareaRequest;
import task.battilana.com.dto.TareaResponse;
import task.battilana.com.entity.CarpetasEntity;
import task.battilana.com.entity.EstadoEnum;
import task.battilana.com.entity.TareasEntity;
import task.battilana.com.entity.UsuariosEntity;
import task.battilana.com.mapper.TareaMapper;
import task.battilana.com.repository.CarpetasRepository;
import task.battilana.com.repository.TareasRepository;
import task.battilana.com.services.TareasService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TareasServiceImpl implements TareasService {

    private final TareaMapper tareaMapper;
    private final TareasRepository tareasRepository;
    private final CarpetasRepository carpetasRepository;

    public TareasServiceImpl(TareaMapper tareaMapper, TareasRepository tareasRepository, CarpetasRepository carpetasRepository) {
        this.tareaMapper = tareaMapper;
        this.tareasRepository = tareasRepository;
        this.carpetasRepository = carpetasRepository;
    }

    @Override
    public List<TareaResponse> listado() {
        return this.tareaMapper.listadoTareaDto(this.tareasRepository.findAll());
    }

    @Override
    public TareaResponse registrar(TareaRequest tareaDto) {
        TareasEntity tareasEntity = this.tareaMapper.tareasEntityMapper(tareaDto);

        if (tareaDto.idCarpeta() != null) {
            CarpetasEntity carpetasEntity = this.carpetasRepository.findById(tareaDto.idCarpeta()).get();
            carpetasEntity.setFechaModificacion(LocalDate.now());
            this.carpetasRepository.save(carpetasEntity);
        } else {
            tareasEntity.setIdCarpeta(null);
        }

        return this.tareaMapper.tareaDtoResponse(this.tareasRepository.save(tareasEntity));
    }

    @Override
    public TareaResponse actualizar(Long id, TareaRequest tareaDto) {
        if (id != null) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            TareasEntity tareasEntity = this.tareasRepository.findById(id).get();
            tareasEntity.setIdTarea(id);
            tareasEntity.setNombreTarea(tareaDto.nombreTarea());
            tareasEntity.setDescripcion(tareaDto.descripcion());
            tareasEntity.setFechaInicio(LocalDate.parse(tareaDto.fechaInicio(), dateTimeFormatter));
            tareasEntity.setFechaFin(LocalDate.parse(tareaDto.fechaFin(), dateTimeFormatter));
            UsuariosEntity usuariosEntity = new UsuariosEntity();
            usuariosEntity.setIdUsuarios(tareaDto.idUsuariosEntity());
            tareasEntity.setUsuariosEntity(usuariosEntity);
            CarpetasEntity carpetasEntity = this.carpetasRepository.findById(tareaDto.idCarpeta()).get();
            tareasEntity.setIdCarpeta(carpetasEntity);

            carpetasEntity.setFechaModificacion(LocalDate.now());
            this.carpetasRepository.save(carpetasEntity);

            return this.tareaMapper.tareaDtoResponse(this.tareasRepository.save(tareasEntity));
        } else {
            return null;
        }
    }

    @Override
    public TareaResponse buscar(Long id) {
        return this.tareaMapper.tareaDtoResponse(this.tareasRepository.findById(id).get());
    }

    @Override
    public void eliminar(Long id) {
        this.tareasRepository.deleteById(id);

        TareasEntity tareasEntity = this.tareasRepository.findById(id).get();
        CarpetasEntity carpetasEntity = this.carpetasRepository.findById(tareasEntity.getIdCarpeta().getIdCarpeta()).get();
        carpetasEntity.setFechaModificacion(LocalDate.now());
        this.carpetasRepository.save(carpetasEntity);
    }

    @Override
    public Integer contadorPendientes() {
        return this.tareasRepository.countByEstadoEnumPendiente();
    }

    @Override
    public Integer contadorTerminados() {
        return this.tareasRepository.countByEstadoEnumTerminado();
    }

    @Override
    public Integer contadorTotales() {
        return this.tareasRepository.countByEstadoEnumTotales();
    }

    @Override
    public void actualizarEstado(Long id) {
        if (id != null) {
            TareasEntity tareasEntity = this.tareasRepository.findById(id).get();
            tareasEntity.setEstadoEnum(EstadoEnum.TERMINADO);
            this.tareasRepository.save(tareasEntity);
        }
    }

    @Override
    public void agregarComentario(Long idTarea, String comentario) {
        if (idTarea != null) {
            Optional<TareasEntity> tareasEntity = this.tareasRepository.findById(idTarea);

            if (comentario != null && !comentario.equalsIgnoreCase("")) {
                if (tareasEntity.isPresent()) {
                    tareasEntity.get().setComentario(comentario);
                    tareasEntity.get().setEstadoEnum(EstadoEnum.TERMINADO);

                    CarpetasEntity carpetasEntity = this.carpetasRepository.findById(tareasEntity.get().getIdCarpeta().getIdCarpeta()).get();
                    carpetasEntity.setFechaModificacion(LocalDate.now());
                    this.carpetasRepository.save(carpetasEntity);

                    this.tareasRepository.save(tareasEntity.get());
                }
            } else {
                if (tareasEntity.isPresent()) {
                    tareasEntity.get().setComentario("");
                    tareasEntity.get().setEstadoEnum(EstadoEnum.TERMINADO);

                    CarpetasEntity carpetasEntity = this.carpetasRepository.findById(tareasEntity.get().getIdCarpeta().getIdCarpeta()).get();
                    carpetasEntity.setFechaModificacion(LocalDate.now());
                    this.carpetasRepository.save(carpetasEntity);

                    this.tareasRepository.save(tareasEntity.get());
                }
            }
        }
    }

    @Override
    public List<TareaResponse> listadoPorPendiente() {
        return this.tareaMapper.listadoTareaDto(this.tareasRepository.listadoTareasPorPendiente());
    }

    @Override
    public List<TareaResponse> listadoPorTerminado() {
        return this.tareaMapper.listadoTareaDto(this.tareasRepository.listadoTareasPorTerminado());
    }

    @Override
    public List<TareaResponse> listadoTareasPendientePorUsuario(Long idUsuario) {
        return this.tareaMapper.listadoTareaDto(this.tareasRepository.listadoTareasPendientesPorUsuario(idUsuario));
    }

    @Override
    public List<TareaResponse> listadoTareasTerminadoPorUsuario(Long idUsuario) {
        return this.tareaMapper.listadoTareaDto(this.tareasRepository.listadoTareasCompletadoPorUsuario(idUsuario));
    }

    @Override
    public List<TareaResponse> listadoTareasTotalPorUsuario(Long idUsuario) {
        return this.tareaMapper.listadoTareaDto(this.tareasRepository.listadoTareasTotalessPorUsuario(idUsuario));
    }

    @Override
    public Integer contadorPendientesPorUsuario(Long idUsuario) {
        return this.tareasRepository.contadorPendientesPorUsuario(idUsuario);
    }

    @Override
    public Integer contadorCompletadoPorUsuario(Long idUsuario) {
        return this.tareasRepository.contadorCompletadoPorUsuario(idUsuario);
    }

    @Override
    public Integer contadorTotalPorUsuario(Long idUsuario) {
        return this.tareasRepository.contadorTotalPorUsuario(idUsuario);
    }

    @Override
    public List<TareaResponse> listadoTareasPorCarpeta(Long idCarpeta, EstadoEnum estado) {
        if (estado != null) {
            return this.tareaMapper.listadoTareaDto(this.tareasRepository.listadoPorCarpetaYEstado(idCarpeta, estado));
        } else {
            return this.tareaMapper.listadoTareaDto(this.tareasRepository.listadoPorCarpeta(idCarpeta));
        }
    }

    @Override
    public Integer contadorPorCarpetasYEstado(Long idCarpeta, EstadoEnum estado) {
        if (estado != null) {
            return this.tareasRepository.contadorPorCarpetaYEstado(idCarpeta, estado);
        } else {
            return this.tareasRepository.contadorPorCarpeta(idCarpeta);
        }
    }

    @Override
    public List<TareaResponse> listadoTareasPorUsuarioSinCarpeta(Long idUsuario) {
        return this.tareaMapper.listadoTareaDto(this.tareasRepository.listadoTareasPorUsuarioYSinCarpeta(idUsuario));
    }

    @Override
    public TareaResponse actualizarCarpetaPorTarea(Long idTarea, Long idCarpeta) {
        TareasEntity tareasEntity = this.tareasRepository.findById(idTarea).get();
        CarpetasEntity carpetasEntity = new CarpetasEntity();
        carpetasEntity.setIdCarpeta(idCarpeta);
        tareasEntity.setIdCarpeta(carpetasEntity);
        return this.tareaMapper.tareaDtoResponse(this.tareasRepository.save(tareasEntity));
    }
}
