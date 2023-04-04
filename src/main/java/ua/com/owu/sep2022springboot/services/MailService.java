package ua.com.owu.sep2022springboot.services;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import ua.com.owu.sep2022springboot.models.Car;


public interface MailService {
    void send(Car car) throws MessagingException;

}
