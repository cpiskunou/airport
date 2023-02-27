package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.service.EmailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String from;

    private final FreeMarkerConfigurer freemarkerConfigurer;
    private final JavaMailSender emailSender;

    @Override
    public void sendMessage(String email) throws IOException, TemplateException, MessagingException {
        Template freemarkerTemplate = freemarkerConfigurer.getConfiguration().getTemplate("mail.ftl");

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("recipientName", "Cichan");
        templateModel.put("text", "Lala");
        templateModel.put("senderName", "I'm");

        String htmlBody = FreeMarkerTemplateUtils.processTemplateIntoString(freemarkerTemplate, templateModel);
        sendSimpleMessage(email, "Forgot password", htmlBody);
    }

    private void sendSimpleMessage(String to, String subject, String htmlBody) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(htmlBody);
        emailSender.send(message);
    }

}
