package task.battilana.com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.battilana.com.dto.LoginRequest;
import task.battilana.com.dto.LoginResponse;
import task.battilana.com.dto.UsuarioRequest;
import task.battilana.com.dto.UsuarioResponse;
import task.battilana.com.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/v1/api/usuario")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @RequestMapping("/listar")
    public ResponseEntity<List<UsuarioResponse>> listado(){
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.listado());
    }

    @GetMapping("/listar/{idUsuario}")
    public ResponseEntity<UsuarioResponse> listadoPorIdUsuario(@PathVariable Long idUsuario){
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.buscar(idUsuario));
    }

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponse> registrar(@RequestBody UsuarioRequest usuariorRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.registrar(usuariorRequest));
    }

    @PutMapping("/actualizar/{idUsuario}")
    public ResponseEntity<UsuarioResponse> actualizar(@PathVariable Long idUsuario, @RequestBody UsuarioRequest usuarioRequest){
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.actualizar(idUsuario, usuarioRequest));
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idUsuario){
        this.usuarioService.eliminar(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.status(HttpStatus.OK).body(this.usuarioService.login(loginRequest));
    }
}
