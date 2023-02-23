package by.piskunou.solvdlaba.service;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {

    void sendMessage(String email) throws IOException, TemplateException, MessagingException;

}
