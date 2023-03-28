package ua.com.owu.sep2022springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.owu.sep2022springboot.models.Car;

import java.util.List;

public interface CarDAO extends JpaRepository<Car, Integer> {


    List<Car> findAllByPower(int power);

    List<Car> findAllByProducer(String producer);

    @Query("select  c from Car c where c.power =:power")
    List<Car> customQueryByPower(int power);

}
