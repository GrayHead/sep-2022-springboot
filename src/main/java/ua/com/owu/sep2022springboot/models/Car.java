package ua.com.owu.sep2022springboot.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.owu.sep2022springboot.views.Views;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({Views.Leve1.class})
    private int id;

    @JsonView({Views.Leve1.class, Views.Leve2.class, Views.Leve3.class})
    private String model;

    @JsonView({Views.Leve1.class, Views.Leve2.class, Views.Leve3.class})
    private String producer;

    @Min(value = 0, message = "power cannot be lt 0")
    @Max(value = 1100, message = "too much power")
    @JsonView({Views.Leve1.class, Views.Leve2.class})
    private int power;

    public Car(String model, String producer, int power) {
        this.model = model;
        this.producer = producer;
        this.power = power;
    }
}
