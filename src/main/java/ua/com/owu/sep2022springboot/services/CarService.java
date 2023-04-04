package ua.com.owu.sep2022springboot.services;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.owu.sep2022springboot.dao.CarDAO;
import ua.com.owu.sep2022springboot.models.Car;

import java.util.List;

@Service

public class CarService {


    private MailService mailService;
    private CarDAO carDAO;

    public CarService(@Qualifier("mailService1") MailService mailService, CarDAO carDAO) {
        this.mailService = mailService;
        this.carDAO = carDAO;
    }

    @SneakyThrows
    public void save(Car car) {
        if (car == null) {
            throw new RuntimeException();
        }
        carDAO.save(car);
        mailService.send(car);
    }

    public ResponseEntity<List<Car>> getCars() {
        Sort by = Sort.by(Sort.Order.desc("id"));
        return new ResponseEntity<>(carDAO.findAll(by), HttpStatus.OK);
    }

    public ResponseEntity<Car> getCar(int id) {
        if (id < 0) {
            throw new RuntimeException();
        }
        return new ResponseEntity<>(carDAO.findById(id).get(), HttpStatus.OK);

    }


}
