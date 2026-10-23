package task.battilana.com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.battilana.com.dto.TareaRequest;
import task.battilana.com.dto.TareaResponse;
import task.battilana.com.entity.EstadoEnum;
import task.battilana.com.services.TareasService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/tareas")
@CrossOrigin("*")
public class TareasController {

    private final TareasService tareasService;

    public TareasController(TareasService tareasService) {
        this.tareasService = tareasService;
    }

    //SECCION DE TAREAS EN GENERAL
    @GetMapping("/listar")
    public ResponseEntity<List<TareaResponse>> listado(){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.listado());
    }

    @PostMapping("/registrar")
    public ResponseEntity<TareaResponse> registrar(@RequestBody TareaRequest tareaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.tareasService.registrar(tareaDto));
    }

    @PutMapping("/actualizar/{idTarea}")
    public ResponseEntity<TareaResponse> actualizar (@PathVariable Long idTarea, @RequestBody TareaRequest tareaRequest){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.actualizar(idTarea, tareaRequest));
    }

    @GetMapping("/buscar/{idTarea}")
    public ResponseEntity<TareaResponse> buscar (@PathVariable Long idTarea){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.buscar(idTarea));
    }

    @DeleteMapping("/eliminar/{idTarea}")
    public ResponseEntity<Void> eliminar (@PathVariable Long idTarea){
        this.tareasService.eliminar(idTarea);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //SECCION DE CONTADORES GENERALES0
    @GetMapping("/contador-pendientes")
    public ResponseEntity<Integer> contadorPendientes (){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.contadorPendientes());
    }

    @GetMapping("/contador-terminados")
    public ResponseEntity<Integer> contadorTerminados(){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.contadorTerminados());
    }

    @GetMapping("/contador-totales")
    public ResponseEntity<Integer> contadorTotales(){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.contadorTotales());
    }

    @PutMapping("/actualizar-estado/{idTarea}")
    public ResponseEntity<Void> actualizarEstado(@PathVariable Long idTarea){
        this.tareasService.actualizarEstado(idTarea);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/actualizar-comentario")
    public ResponseEntity<Void> actualizarComentaroYEstado(@RequestParam("idTarea") Long idTarea, @RequestParam(value = "comentario", required = false) String comentario){
        this.tareasService.agregarComentario(idTarea, comentario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listado-pendientes")
    public ResponseEntity<List<TareaResponse>> listadoPendientes(){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.listadoPorPendiente());
    }

    @GetMapping("/listado-terminado")
    public ResponseEntity<List<TareaResponse>> listadoTerminados(){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.listadoPorTerminado());
    }

    @GetMapping("/listado-pendiente/usuario/{idUsuario}")
    public ResponseEntity<List<TareaResponse>> listadoPendientePorUsuario(@PathVariable Long idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.listadoTareasPendientePorUsuario(idUsuario));
    }

    @GetMapping("/listado-terminado/usuario/{idUsuario}")
    public ResponseEntity<List<TareaResponse>> listadoTerminadoPorUsuario(@PathVariable Long idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.listadoTareasTerminadoPorUsuario(idUsuario));
    }

    @GetMapping("/listado-total/usuario/{idUsuario}")
    public ResponseEntity<List<TareaResponse>> listadoTotalPorUsuario(@PathVariable Long idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.listadoTareasTotalPorUsuario(idUsuario));
    }

    @GetMapping("/contador-pendientes/usuario/{idUsuario}")
    public ResponseEntity<Integer> contadorPendientesPorUsuario (@PathVariable Long idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.contadorPendientesPorUsuario(idUsuario));
    }

    @GetMapping("/contador-completado/usuario/{idUsuario}")
    public ResponseEntity<Integer> contadorCompletoPorUsuario (@PathVariable Long idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.contadorCompletadoPorUsuario(idUsuario));
    }

    @GetMapping("/contador-total/usuario/{idUsuario}")
    public ResponseEntity<Integer> contadorTotalPorUsuario (@PathVariable Long idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.contadorTotalPorUsuario(idUsuario));
    }

    //SECCION TAREAS POR CARPETA
    @GetMapping("/listado/carpeta")
    public ResponseEntity<List<TareaResponse>> listadoTareasPorCarpetaYEstado(@RequestParam("idCarpeta") Long idCarpeta, @RequestParam(value = "estado", required = false) EstadoEnum estado){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.listadoTareasPorCarpeta(idCarpeta, estado));
    }

    @GetMapping("/contador/carpeta")
    public ResponseEntity<Integer> contadorPorCarpetaYEstado(@RequestParam("idCarpeta") Long idCarpeta, @RequestParam(value = "estado", required = false) EstadoEnum estado){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.contadorPorCarpetasYEstado(idCarpeta, estado));
    }

    //SECCION TAREAS POR USUARIO Y SIN CARPETA
    @GetMapping("/listado/usuario/{idUsuario}/sin-carpeta")
    public ResponseEntity<List<TareaResponse>> listadoTareasPorUsuarioSinCarpeta(@PathVariable Long idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.listadoTareasPorUsuarioSinCarpeta(idUsuario));
    }

    @PutMapping("/actualizar/tarea/{idTarea}/carpeta/{idCarpeta}")
    public ResponseEntity<TareaResponse> actualizarCarpetaPorTarea(@PathVariable Long idTarea, @PathVariable Long idCarpeta){
        return ResponseEntity.status(HttpStatus.OK).body(this.tareasService.actualizarCarpetaPorTarea(idTarea, idCarpeta));
    }
}
