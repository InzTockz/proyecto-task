package task.battilana.com.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import task.battilana.com.dto.TareaRequest;
import task.battilana.com.dto.TareaResponse;
import task.battilana.com.entity.CarpetasEntity;
import task.battilana.com.entity.EstadoEnum;
import task.battilana.com.entity.TareasEntity;
import task.battilana.com.entity.UsuariosEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-07T14:13:52-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class TareaMapperImpl implements TareaMapper {

    @Override
    public List<TareaResponse> listadoTareaDto(List<TareasEntity> listadoTareasEntity) {
        if ( listadoTareasEntity == null ) {
            return null;
        }

        List<TareaResponse> list = new ArrayList<TareaResponse>( listadoTareasEntity.size() );
        for ( TareasEntity tareasEntity : listadoTareasEntity ) {
            list.add( tareaDtoResponse( tareasEntity ) );
        }

        return list;
    }

    @Override
    public TareaResponse tareaDtoResponse(TareasEntity tareasEntity) {
        if ( tareasEntity == null ) {
            return null;
        }

        Long idUsuariosEntity = null;
        EstadoEnum estado = null;
        String nombreUsuarioEntity = null;
        Long idCarpeta = null;
        Long idTarea = null;
        String nombreTarea = null;
        String descripcion = null;
        LocalDate fechaCreacion = null;
        String fechaInicio = null;
        String fechaFin = null;
        String comentario = null;

        idUsuariosEntity = tareasEntityUsuariosEntityIdUsuarios( tareasEntity );
        estado = tareasEntity.getEstadoEnum();
        nombreUsuarioEntity = tareasEntityUsuariosEntityNombres( tareasEntity );
        idCarpeta = tareasEntityIdCarpetaIdCarpeta( tareasEntity );
        idTarea = tareasEntity.getIdTarea();
        nombreTarea = tareasEntity.getNombreTarea();
        descripcion = tareasEntity.getDescripcion();
        fechaCreacion = tareasEntity.getFechaCreacion();
        if ( tareasEntity.getFechaInicio() != null ) {
            fechaInicio = DateTimeFormatter.ISO_LOCAL_DATE.format( tareasEntity.getFechaInicio() );
        }
        if ( tareasEntity.getFechaFin() != null ) {
            fechaFin = DateTimeFormatter.ISO_LOCAL_DATE.format( tareasEntity.getFechaFin() );
        }
        comentario = tareasEntity.getComentario();

        TareaResponse tareaResponse = new TareaResponse( idTarea, nombreTarea, descripcion, estado, fechaCreacion, fechaInicio, fechaFin, comentario, idUsuariosEntity, nombreUsuarioEntity, idCarpeta );

        return tareaResponse;
    }

    @Override
    public TareasEntity tareasEntityMapper(TareaRequest tareaDto) {
        if ( tareaDto == null ) {
            return null;
        }

        TareasEntity tareasEntity = new TareasEntity();

        tareasEntity.setUsuariosEntity( tareaRequestToUsuariosEntity( tareaDto ) );
        tareasEntity.setIdCarpeta( tareaRequestToCarpetasEntity( tareaDto ) );
        tareasEntity.setNombreTarea( tareaDto.nombreTarea() );
        tareasEntity.setDescripcion( tareaDto.descripcion() );
        if ( tareaDto.fechaInicio() != null ) {
            tareasEntity.setFechaInicio( LocalDate.parse( tareaDto.fechaInicio() ) );
        }
        if ( tareaDto.fechaFin() != null ) {
            tareasEntity.setFechaFin( LocalDate.parse( tareaDto.fechaFin() ) );
        }

        return tareasEntity;
    }

    private Long tareasEntityUsuariosEntityIdUsuarios(TareasEntity tareasEntity) {
        if ( tareasEntity == null ) {
            return null;
        }
        UsuariosEntity usuariosEntity = tareasEntity.getUsuariosEntity();
        if ( usuariosEntity == null ) {
            return null;
        }
        Long idUsuarios = usuariosEntity.getIdUsuarios();
        if ( idUsuarios == null ) {
            return null;
        }
        return idUsuarios;
    }

    private String tareasEntityUsuariosEntityNombres(TareasEntity tareasEntity) {
        if ( tareasEntity == null ) {
            return null;
        }
        UsuariosEntity usuariosEntity = tareasEntity.getUsuariosEntity();
        if ( usuariosEntity == null ) {
            return null;
        }
        String nombres = usuariosEntity.getNombres();
        if ( nombres == null ) {
            return null;
        }
        return nombres;
    }

    private Long tareasEntityIdCarpetaIdCarpeta(TareasEntity tareasEntity) {
        if ( tareasEntity == null ) {
            return null;
        }
        CarpetasEntity idCarpeta = tareasEntity.getIdCarpeta();
        if ( idCarpeta == null ) {
            return null;
        }
        Long idCarpeta1 = idCarpeta.getIdCarpeta();
        if ( idCarpeta1 == null ) {
            return null;
        }
        return idCarpeta1;
    }

    protected UsuariosEntity tareaRequestToUsuariosEntity(TareaRequest tareaRequest) {
        if ( tareaRequest == null ) {
            return null;
        }

        UsuariosEntity usuariosEntity = new UsuariosEntity();

        usuariosEntity.setIdUsuarios( tareaRequest.idUsuariosEntity() );

        return usuariosEntity;
    }

    protected CarpetasEntity tareaRequestToCarpetasEntity(TareaRequest tareaRequest) {
        if ( tareaRequest == null ) {
            return null;
        }

        CarpetasEntity carpetasEntity = new CarpetasEntity();

        carpetasEntity.setIdCarpeta( tareaRequest.idCarpeta() );

        return carpetasEntity;
    }
}
