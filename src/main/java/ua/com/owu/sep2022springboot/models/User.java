package ua.com.owu.sep2022springboot.models;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import ua.com.owu.sep2022springboot.views.Views;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(value = Views.Level2.class)
    private int id;


    //    @NotNull
//    @NotEmpty
//    @Pattern(regexp = '[a-z]')
    @NotBlank(message = "name cannot be empty")
    @Size(min = 2, message = "name too short")
    @Size(max = 6, message = "name too long")


    @JsonView(value = {Views.Level1.class, Views.Level2.class})
    private String name;

    @Min(value = 0, message = "age cannot be lt zero")
    @Max(value = 123, message = "too old")

    @JsonView(value = {Views.Level1.class, Views.Level2.class})
    private int age;

    public User(String name) {
        this.name = name;
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
