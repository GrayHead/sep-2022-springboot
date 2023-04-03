package ua.com.owu.sep2022springboot.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.owu.sep2022springboot.models.User;

@Service
@AllArgsConstructor
public class MailService {

    private JavaMailSender javaMailSender;

    public void sendEmail(User user) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setTo("ss.zhuravlov@gmail.com");
            helper.setText("<h2>user " + user.toString() + " created</h2>", true);
            helper.setFrom(new InternetAddress("mr.java2022@gmail.com"));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        javaMailSender.send(mimeMessage);


    }
}
