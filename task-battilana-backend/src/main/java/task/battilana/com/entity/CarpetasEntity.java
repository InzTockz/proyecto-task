package task.battilana.com.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity()
@Table(name = "tbl_carpetas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarpetasEntity {

    @Id
    @Column(name = "id_carpetas")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarpeta;

    @Column(name = "nombre_carpeta")
    private String nombreCarpeta;

    @Column(name = "estado")
    private boolean estado = true;

    @Column(name = "fecha_creacion")
    @CreationTimestamp
    private LocalDate fechaCreacion;

    @Column(name = "fecha_modificacion")
    @UpdateTimestamp
    private LocalDate fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuariosEntity idUsuario;
}
