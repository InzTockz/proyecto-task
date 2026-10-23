package task.battilana.com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.battilana.com.dto.CarpetaRequest;
import task.battilana.com.dto.CarpetaResponse;
import task.battilana.com.services.CarpetasService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/api/carpetas")
@CrossOrigin("*")
public class CarpetasController {

    private final CarpetasService carpetasService;

    public CarpetasController(CarpetasService carpetasService) {
        this.carpetasService = carpetasService;
    }

    @GetMapping("/listado")
    public ResponseEntity<List<CarpetaResponse>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(this.carpetasService.listado());
    }

    @PostMapping("/registrar")
    public ResponseEntity<CarpetaResponse> registrar(@RequestBody CarpetaRequest carpetaRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.carpetasService.registrar(carpetaRequest));
    }

    @GetMapping("/buscar/{idUsuario}")
    public ResponseEntity<List<CarpetaResponse>> buscarCarpetasPorId(@PathVariable Long idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(this.carpetasService.buscarListaId(idUsuario));
    }

    @GetMapping("/buscar/carpeta/{idUsuario}")
    public ResponseEntity<CarpetaResponse> buscarCarpetaPorId(@PathVariable Long idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(this.carpetasService.buscarId(idUsuario));
    }

    @GetMapping("/buscar/carpeta/fecha/estados")
    public ResponseEntity<List<CarpetaResponse>> buscarCarpetaPorUsuarioFechaYEstado(
            @RequestParam(value = "firstDate", required = false) LocalDate firstDate, @RequestParam(value = "lastDate", required = false) LocalDate lastDate,
            @RequestParam("idUsuario") Long idUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(this.carpetasService.buscarCarpetaPorUsuarioFechaYEstado(idUsuario, firstDate, lastDate));
    }
}
