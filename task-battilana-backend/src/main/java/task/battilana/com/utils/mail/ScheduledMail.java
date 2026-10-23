package task.battilana.com.utils.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import task.battilana.com.entity.TareasEntity;
import task.battilana.com.entity.UsuariosEntity;
import task.battilana.com.repository.TareasRepository;
import task.battilana.com.repository.UsuarioRepository;

import java.util.Date;
import java.util.List;

@Component
public class ScheduledMail {

    private final JavaMailSender javaMailSender;
    private final Logger logger = LoggerFactory.getLogger(ScheduledMail.class);
    private final TareasRepository tareasRepository;
    private final UsuarioRepository usuarioRepository;
    private final TemplateEngine templateEngine;

    public ScheduledMail(JavaMailSender javaMailSender, TareasRepository tareasRepository, UsuarioRepository usuarioRepository, TemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.tareasRepository = tareasRepository;
        this.usuarioRepository = usuarioRepository;
        this.templateEngine = templateEngine;
    }

    @Scheduled(cron = "0 0 7 ? * MON-FRI", zone = "America/Lima")
//    @Scheduled(cron = "10 * * ? * *", zone = "America/Lima")
    public void enviarMail() {
        try {
            List<UsuariosEntity> usuariosEntity = this.usuarioRepository.findAll();

            if (!usuariosEntity.isEmpty()) {
                for (UsuariosEntity usuarios : usuariosEntity) {
                    List<TareasEntity> tareasEntity = this.tareasRepository.listadoTareasPorUsuario(usuarios.getIdUsuarios());

                    if (!tareasEntity.isEmpty()) {
                        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
                        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                        helper.setFrom("taskbattilana@battilana.biz");
                        helper.setTo(usuarios.getCorreo());
                        helper.setSubject("Recordatorio: Tareas pendientes por completar");

                        Context context = new Context();
                        context.setVariable("nombre", usuarios.getNombres());
                        context.setVariable("listaTareas", tareasEntity);
                        String html = this.templateEngine.process("mail-automatic", context);

                        helper.setText(html, true);

                        helper.addInline("pendingIcon",
                                new ClassPathResource("templates/static/image/pending.png"), "image/png");

                        this.javaMailSender.send(mimeMessage);
                        logger.info("Correo enviado: {}", new Date());
                    }
                }
            }
        } catch (MessagingException ex) {
            System.out.println("Error: " + ex);
        }
    }
}
