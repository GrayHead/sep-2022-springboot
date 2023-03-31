package ua.com.owu.sep2022springboot.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.sep2022springboot.dao.CarDAO;
import ua.com.owu.sep2022springboot.models.Car;
import ua.com.owu.sep2022springboot.views.Views;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cars")
public class MainController {


    private CarDAO carDAO;


    @GetMapping("")
    @JsonView(Views.Leve3.class)
    public ResponseEntity<List<Car>> getCars() {
        return new ResponseEntity<>(carDAO.findAll(Sort.by(Sort.Order.desc("id"))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(Views.Leve1.class)
    public ResponseEntity<Car> getCar(@PathVariable int id) {
        return new ResponseEntity<>(carDAO.findById(id).get(), HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCar(@RequestBody @Valid Car car) {
        carDAO.save(car);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable int id) {
        carDAO.deleteById(id);
    }

//    get cars/power/{value} (знайти всі по потужності)

    @GetMapping("/power/{value}")
    @JsonView(Views.Leve2.class)
    public ResponseEntity<List<Car>> getCarsByPower(@PathVariable int value) {
//        return carDAO.findAllByPower(value);
        return new ResponseEntity<>(carDAO.customQueryByPower(value), HttpStatus.OK);
    }

    //    get cars/producer/{value} (знайти всі по виробнику)
    @GetMapping("/producer/{value}")
    @JsonView(Views.Leve2.class)

    public ResponseEntity<List<Car>> getCarsByProducer(@PathVariable String value) {
        return new ResponseEntity<>(carDAO.findAllByProducer(value), HttpStatusCode.valueOf(200));
    }

}
