package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.service.EmailService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
        sendHtmlMessage(email, "Forgot password", htmlBody);
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        emailSender.send(message);
    }

}
