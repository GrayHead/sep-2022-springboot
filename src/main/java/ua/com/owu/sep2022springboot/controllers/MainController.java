package ua.com.owu.sep2022springboot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.sep2022springboot.dao.CarDAO;
import ua.com.owu.sep2022springboot.models.Car;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cars")
public class MainController {

    private CarDAO carDAO;

    @GetMapping("")
    public List<Car> getCars() {
        Sort by = Sort.by(Sort.Order.desc("id"));
        return carDAO.findAll(by);
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable int id) {
        return carDAO.findById(id).get();
    }

    @PostMapping("")
    public void saveCar(@RequestBody Car car) {
        carDAO.save(car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id) {
        carDAO.deleteById(id);
    }

//    get cars/power/{value} (знайти всі по потужності)

    @GetMapping("/power/{value}")
    public List<Car> getCarsByPower(@PathVariable int value) {
//        return carDAO.findAllByPower(value);
        return carDAO.customQueryByPower(value);
    }

    //    get cars/producer/{value} (знайти всі по виробнику)
    @GetMapping("/producer/{value}")
    public List<Car> getCarsByProducer(@PathVariable String value) {
        return carDAO.findAllByProducer(value);
    }

}
