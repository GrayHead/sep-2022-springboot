package ua.com.owu.sep2022springboot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.sep2022springboot.models.Car;
import ua.com.owu.sep2022springboot.services.CarService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cars")
public class MainController {

    private CarService carService;


    @GetMapping("")
    public ResponseEntity<List<Car>> getCars() {
        return carService.getCars();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable int id) {
        return carService.getCar(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void saveCar(@RequestBody Car car) {
        carService.save(car);
    }

//    @DeleteMapping("/{id}")
//    public void deleteCar(@PathVariable int id) {
//        carDAO.deleteById(id);
//    }
//
////    get cars/power/{value} (знайти всі по потужності)
//
//    @GetMapping("/power/{value}")
//    public List<Car> getCarsByPower(@PathVariable int value) {
////        return carDAO.findAllByPower(value);
//        return carDAO.customQueryByPower(value);
//    }
//
//    //    get cars/producer/{value} (знайти всі по виробнику)
//    @GetMapping("/producer/{value}")
//    public List<Car> getCarsByProducer(@PathVariable String value) {
//        return carDAO.findAllByProducer(value);
//    }

}
