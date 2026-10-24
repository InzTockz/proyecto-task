package task.battilana.com.services;

import jakarta.mail.MessagingException;

public interface EmailService {

    void reenviarEstadoEmail(Long idUsuario) throws MessagingException;
}
