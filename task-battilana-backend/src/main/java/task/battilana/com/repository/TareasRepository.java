package task.battilana.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task.battilana.com.entity.CarpetasEntity;
import task.battilana.com.entity.EstadoEnum;
import task.battilana.com.entity.TareasEntity;

import java.util.List;

public interface TareasRepository extends JpaRepository<TareasEntity, Long> {

    //CONTADOR DE TAREAS POR ESTADO
    @Query("SELECT count(te) " +
            "FROM TareasEntity te " +
            "WHERE te.estadoEnum = 'PENDIENTE'")
    int countByEstadoEnumPendiente();

    @Query("SELECT count(te) " +
            "FROM TareasEntity te " +
            "WHERE te.estadoEnum = 'TERMINADO'")
    int countByEstadoEnumTerminado();

    @Query("SELECT count(T) FROM TareasEntity T ")
    int countByEstadoEnumTotales();

    //LISTADO DE TAREAS POR ESTADO
    @Query("SELECT T FROM TareasEntity T " +
            "WHERE T.estadoEnum = 'PENDIENTE' " +
            "ORDER BY T.fechaCreacion desc")
    List<TareasEntity> listadoTareasPorPendiente();

    @Query("SELECT T FROM TareasEntity T " +
            "WHERE T.estadoEnum = 'TERMINADO' " +
            "ORDER BY T.fechaCreacion desc")
    List<TareasEntity> listadoTareasPorTerminado();

    @Query("SELECT T FROM TareasEntity T " +
            "ORDER BY T.fechaCreacion desc")
    List<TareasEntity> findAll();

    //PARA ENVIOS DE CORREO DE MANERA AUTOMATICA
    @Query("SELECT T " +
            "FROM TareasEntity T " +
            "INNER JOIN UsuariosEntity U ON U.idUsuarios = T.usuariosEntity.idUsuarios " +
            "WHERE T.usuariosEntity.idUsuarios =:idUsuario AND T.estadoEnum='PENDIENTE'")
    List<TareasEntity> listadoTareasPorUsuario(@Param("idUsuario") Long idUsuario);

    //LISTADO DE TAREAS POR USUARIO
    @Query("SELECT TP " +
            "FROM TareasEntity TP " +
            "WHERE TP.usuariosEntity.idUsuarios = :idUsuario AND TP.estadoEnum = 'PENDIENTE'")
    List<TareasEntity> listadoTareasPendientesPorUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT TP " +
            "FROM TareasEntity TP " +
            "WHERE TP.usuariosEntity.idUsuarios = :idUsuario AND TP.estadoEnum = 'TERMINADO'")
    List<TareasEntity> listadoTareasCompletadoPorUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT TP " +
            "FROM TareasEntity TP " +
            "WHERE TP.usuariosEntity.idUsuarios = :idUsuario")
    List<TareasEntity> listadoTareasTotalessPorUsuario(@Param("idUsuario") Long idUsuario);

    //CONTADOR DE TAREAS POR USUARIO
    @Query("SELECT COUNT(T.idTarea) " +
            "FROM TareasEntity T " +
            "WHERE T.usuariosEntity.idUsuarios=:idUsuario AND T.estadoEnum=:estado")
    Integer contadorEstadoPorUsuario(@Param("idUsuario") Long idUsuario, @Param("estado") EstadoEnum estado);

    @Query("SELECT COUNT(T.idTarea) " +
            "FROM TareasEntity T " +
            "WHERE T.usuariosEntity.idUsuarios=:idUsuario")
    Integer contadorPorUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT COUNT(T.idTarea) " +
            "FROM TareasEntity T " +
            "WHERE T.usuariosEntity.idUsuarios=:idUsuario AND T.estadoEnum='PENDIENTE'")
    Integer contadorPendientesPorUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT COUNT(T.idTarea) " +
            "FROM TareasEntity T " +
            "WHERE T.usuariosEntity.idUsuarios=:idUsuario AND T.estadoEnum='TERMINADO'")
    Integer contadorCompletadoPorUsuario(@Param("idUsuario") Long idUsuario);

    @Query("SELECT COUNT(T.idTarea) " +
            "FROM TareasEntity T " +
            "WHERE T.usuariosEntity.idUsuarios=:idUsuario")
    Integer contadorTotalPorUsuario(@Param("idUsuario") Long idUsuario);

    //LISTADO POR CARPETA Y ESTADO
    @Query("SELECT T " +
            "FROM TareasEntity T " +
            "INNER JOIN CarpetasEntity C ON C.idCarpeta = T.idCarpeta.idCarpeta " +
            "WHERE T.idCarpeta.idCarpeta =:idCarpeta AND T.estadoEnum=:estado")
    List<TareasEntity> listadoPorCarpetaYEstado(@Param("idCarpeta") Long idCarpeta, @Param("estado") EstadoEnum estado);

    @Query("SELECT T " +
            "FROM TareasEntity T " +
            "INNER JOIN CarpetasEntity C ON C.idCarpeta = T.idCarpeta.idCarpeta " +
            "WHERE T.idCarpeta.idCarpeta =:idCarpeta")
    List<TareasEntity> listadoPorCarpeta(@Param("idCarpeta") Long idCarpeta);

    //CONTADORES POR USUARIO Y ESTADO
    @Query("SELECT count(T.idTarea) " +
            "FROM TareasEntity T " +
            "INNER JOIN CarpetasEntity C ON C.idCarpeta = T.idCarpeta.idCarpeta " +
            "WHERE T.idCarpeta.idCarpeta =:idCarpeta AND T.estadoEnum=:estado")
    Integer contadorPorCarpetaYEstado(@Param("idCarpeta") Long idCarpeta, @Param("estado") EstadoEnum estado);

    @Query("SELECT count(T.idTarea) " +
            "FROM TareasEntity T " +
            "INNER JOIN CarpetasEntity C ON C.idCarpeta = T.idCarpeta.idCarpeta " +
            "WHERE T.idCarpeta.idCarpeta =:idCarpeta")
    Integer contadorPorCarpeta(@Param("idCarpeta") Long idCarpeta);

    //LISTADO DE TAREAS SIN CARPETA
    @Query("SELECT T " +
            "FROM TareasEntity T " +
            "WHERE T.usuariosEntity.idUsuarios=:idUsuario AND T.idCarpeta IS NULL")
    List<TareasEntity> listadoTareasPorUsuarioYSinCarpeta(@Param("idUsuario") Long idUsuario);
}
