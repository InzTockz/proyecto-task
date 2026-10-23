package task.battilana.com.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_tareas")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TareasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tareas")
    private Long idTarea;
    @Column(name = "nombre_tarea")
    private String nombreTarea;
    @Column(name = "descripcion_tarea")
    private String descripcion;
    @Column(name = "estado")
    @Enumerated(EnumType.STRING)
    private EstadoEnum estadoEnum = EstadoEnum.PENDIENTE;
    @Column(name = "fecha_creacion")
    @CreationTimestamp
    private LocalDate fechaCreacion;
    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;
    @Column(name = "fecha_fin")
    private LocalDate fechaFin;
    @Column(name = "comentario")
    private String comentario = "";

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuariosEntity usuariosEntity;

    @ManyToOne
    @JoinColumn(name = "id_carpeta")
    private CarpetasEntity idCarpeta;
}
