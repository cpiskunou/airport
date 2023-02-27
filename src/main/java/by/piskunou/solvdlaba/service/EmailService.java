package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.Email;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {

    void sendMessage(Email email) throws IOException, TemplateException, MessagingException;

}
