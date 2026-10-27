package task.battilana.com.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import task.battilana.com.entity.TareasEntity;
import task.battilana.com.entity.UsuariosEntity;
import task.battilana.com.repository.TareasRepository;
import task.battilana.com.repository.UsuarioRepository;
import task.battilana.com.services.EmailService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${credenciales.value.email-emisor}")
    String emailEmisor;

    private final UsuarioRepository usuarioRepository;
    private final JavaMailSender jms;
    private final TemplateEngine templateEngine;
    private final TareasRepository tareasRepository;

    @Override
    public void reenviarEstadoEmail(Long idUsuario)  {

            UsuariosEntity usuarioEncontrado = this.usuarioRepository.findById(idUsuario).orElseThrow(
                    () -> new EntityNotFoundException("Usuario con id: " + idUsuario + " no existe.")
            );

            List<TareasEntity> tareasPorUsuario = this.tareasRepository.listadoTareasPorUsuario(usuarioEncontrado.getIdUsuarios());

            try {

                MimeMessage ms = this.jms.createMimeMessage();
                MimeMessageHelper msh = new MimeMessageHelper(ms, true, "UTF-8");

                msh.setFrom(emailEmisor);
                msh.setTo(usuarioEncontrado.getCorreo());
                msh.setSubject("Recordatorio: Tareas pendientes por completar");

                Context context = new Context();
                context.setVariable("nombre", usuarioEncontrado.getNombres());
                context.setVariable("listaTareas", tareasPorUsuario);
                String html = this.templateEngine.process("mail-automatic", context);

                msh.setText(html, true);

                msh.addInline("pendingIcon", new ClassPathResource("templates/static/image/pending.png"), "image/png");

                this.jms.send(ms);
            } catch (MessagingException me){
                System.out.println(me.getMessage());
            }

    }
}
