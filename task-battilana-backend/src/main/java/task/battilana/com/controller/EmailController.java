package task.battilana.com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.battilana.com.services.EmailService;

@RestController
@RequestMapping("/api/v1/email")
@CrossOrigin("*")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/{idUsuario}")
    public ResponseEntity<Void> reenviarEstadoEmail(@PathVariable Long idUsuario){
        this.emailService.reenviarEstadoEmail(idUsuario);
        return ResponseEntity.noContent().build();
    }

}
