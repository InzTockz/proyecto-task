package task.battilana.com.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import task.battilana.com.dto.CarpetaRequest;
import task.battilana.com.dto.CarpetaResponse;
import task.battilana.com.entity.CarpetasEntity;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarpetaMapper {

    @Mapping(target = "idUsuario", source = "idUsuario.idUsuarios")
    List<CarpetaResponse> toListCarpetaResponse(List<CarpetasEntity> carpetasEntities);

    @Mapping(target = "idUsuario", source = "idUsuario.idUsuarios")
    CarpetaResponse toCarpetaResponse(CarpetasEntity carpetasEntity);

    @Mapping(target = "idUsuario.idUsuarios", source = "idUsuario")
    CarpetasEntity toCarpetaEntity(CarpetaRequest carpetaRequest);
}
