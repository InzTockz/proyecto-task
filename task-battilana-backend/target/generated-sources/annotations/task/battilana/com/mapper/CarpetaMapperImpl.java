package task.battilana.com.mapper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import task.battilana.com.dto.CarpetaRequest;
import task.battilana.com.dto.CarpetaResponse;
import task.battilana.com.entity.CarpetasEntity;
import task.battilana.com.entity.UsuariosEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-07T14:13:52-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.5 (Eclipse Adoptium)"
)
@Component
public class CarpetaMapperImpl implements CarpetaMapper {

    @Override
    public List<CarpetaResponse> toListCarpetaResponse(List<CarpetasEntity> carpetasEntities) {
        if ( carpetasEntities == null ) {
            return null;
        }

        List<CarpetaResponse> list = new ArrayList<CarpetaResponse>( carpetasEntities.size() );
        for ( CarpetasEntity carpetasEntity : carpetasEntities ) {
            list.add( toCarpetaResponse( carpetasEntity ) );
        }

        return list;
    }

    @Override
    public CarpetaResponse toCarpetaResponse(CarpetasEntity carpetasEntity) {
        if ( carpetasEntity == null ) {
            return null;
        }

        Long idUsuario = null;
        Long idCarpeta = null;
        String nombreCarpeta = null;
        String fechaCreacion = null;
        String fechaModificacion = null;

        idUsuario = carpetasEntityIdUsuarioIdUsuarios( carpetasEntity );
        idCarpeta = carpetasEntity.getIdCarpeta();
        nombreCarpeta = carpetasEntity.getNombreCarpeta();
        if ( carpetasEntity.getFechaCreacion() != null ) {
            fechaCreacion = DateTimeFormatter.ISO_LOCAL_DATE.format( carpetasEntity.getFechaCreacion() );
        }
        if ( carpetasEntity.getFechaModificacion() != null ) {
            fechaModificacion = DateTimeFormatter.ISO_LOCAL_DATE.format( carpetasEntity.getFechaModificacion() );
        }

        CarpetaResponse carpetaResponse = new CarpetaResponse( idCarpeta, nombreCarpeta, fechaCreacion, fechaModificacion, idUsuario );

        return carpetaResponse;
    }

    @Override
    public CarpetasEntity toCarpetaEntity(CarpetaRequest carpetaRequest) {
        if ( carpetaRequest == null ) {
            return null;
        }

        CarpetasEntity carpetasEntity = new CarpetasEntity();

        carpetasEntity.setIdUsuario( carpetaRequestToUsuariosEntity( carpetaRequest ) );
        carpetasEntity.setNombreCarpeta( carpetaRequest.nombreCarpeta() );

        return carpetasEntity;
    }

    private Long carpetasEntityIdUsuarioIdUsuarios(CarpetasEntity carpetasEntity) {
        if ( carpetasEntity == null ) {
            return null;
        }
        UsuariosEntity idUsuario = carpetasEntity.getIdUsuario();
        if ( idUsuario == null ) {
            return null;
        }
        Long idUsuarios = idUsuario.getIdUsuarios();
        if ( idUsuarios == null ) {
            return null;
        }
        return idUsuarios;
    }

    protected UsuariosEntity carpetaRequestToUsuariosEntity(CarpetaRequest carpetaRequest) {
        if ( carpetaRequest == null ) {
            return null;
        }

        UsuariosEntity usuariosEntity = new UsuariosEntity();

        usuariosEntity.setIdUsuarios( carpetaRequest.idUsuario() );

        return usuariosEntity;
    }
}
