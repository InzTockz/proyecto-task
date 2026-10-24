package task.battilana.com.serviceimpl;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import task.battilana.com.entity.TareasEntity;
import task.battilana.com.entity.UsuariosEntity;
import task.battilana.com.repository.TareasRepository;
import task.battilana.com.repository.UsuarioRepository;
import task.battilana.com.services.impl.EmailServiceImpl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceImplTest {

    @Mock private UsuarioRepository usuarioRepository;
    @Mock private TareasRepository tareasRepository;
    @Mock private TemplateEngine templateEngine;
    @Mock private JavaMailSender jms;

    private MimeMessage mimeMessage;
    private UsuariosEntity usuario;

    @InjectMocks private EmailServiceImpl emailService;

    @BeforeEach
    void setup(){
        mimeMessage = new MimeMessage((Session) null);
        usuario = new UsuariosEntity();
        usuario.setIdUsuarios(1L);
        usuario.setNombres("Fredy");
        usuario.setCorreo("fhurtado@battilana.biz");
    }

    @Test
    void enviar_correoUsuario() throws Exception {
        List<TareasEntity> tareas = List.of(new TareasEntity(), new TareasEntity());
        String html = "<html><body>Hola Fredy</body?</html>";

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(tareasRepository.listadoTareasPorUsuario(1L)).thenReturn(tareas);
        when(jms.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(eq("mail-automatic"), any(Context.class))).thenReturn(html);

        emailService.reenviarEstadoEmail(1L);

        verify(jms).send(mimeMessage);
    }
}
