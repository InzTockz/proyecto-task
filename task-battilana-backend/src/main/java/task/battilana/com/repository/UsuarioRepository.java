package task.battilana.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task.battilana.com.entity.UsuariosEntity;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuariosEntity, Long> {

    UsuariosEntity findByCorreo(String correo);
    List<UsuariosEntity> findByEstadoTrue();
}
