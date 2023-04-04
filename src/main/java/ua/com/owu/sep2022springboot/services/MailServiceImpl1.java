package ua.com.owu.sep2022springboot.services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.com.owu.sep2022springboot.models.Car;

@Service("mailService1")
@AllArgsConstructor
public class MailServiceImpl1 implements MailService {
    private JavaMailSender javaMailSender;


    @Override
    public void send(Car car) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo("ss.zhuravlov@gmail.com");
        helper.setText(car.toString());
        javaMailSender.send(mimeMessage);


    }
}
