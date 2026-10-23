package task.battilana.com.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import task.battilana.com.dto.TareaRequest;
import task.battilana.com.dto.TareaResponse;
import task.battilana.com.entity.TareasEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TareaMapper {

    @Mapping(target = "idUsuariosEntity", source = "usuariosEntity.idUsuarios")
    @Mapping(target = "nombreUsuarioEntity", source = "usuariosEntity.nombres")
    @Mapping(target = "idCarpeta", source = "idCarpeta.idCarpeta")
    List<TareaResponse> listadoTareaDto(List<TareasEntity> listadoTareasEntity);

    @Mapping(target = "idUsuariosEntity", source = "usuariosEntity.idUsuarios")
    @Mapping(target = "estado", source = "estadoEnum")
    @Mapping(target = "nombreUsuarioEntity", source = "usuariosEntity.nombres")
    @Mapping(target = "idCarpeta", source = "idCarpeta.idCarpeta")
    TareaResponse tareaDtoResponse (TareasEntity tareasEntity);

    @Mapping(target = "usuariosEntity.idUsuarios", source = "idUsuariosEntity")
    @Mapping(target= "idCarpeta.idCarpeta", source = "idCarpeta")
    TareasEntity tareasEntityMapper (TareaRequest tareaDto);

}
