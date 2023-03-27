package ua.com.owu.sep2022springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import ua.com.owu.sep2022springboot.actions.Actions;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3000")
public class Sep2022SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sep2022SpringbootApplication.class, args);
        Actions.GetActions getActions = new Actions().new GetActions();

    }

}
