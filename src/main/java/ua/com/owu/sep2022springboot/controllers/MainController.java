package ua.com.owu.sep2022springboot.controllers;

import org.springframework.web.bind.annotation.*;
import ua.com.owu.sep2022springboot.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MainController {
    private List<User> users = new ArrayList<>();

    public MainController() {
        users.add(new User(2, "kokos"));
        users.add(new User(1, "ananas"));
        users.add(new User(3, "banan"));
        users.add(new User(5, "tomat"));
        users.add(new User(4, "potatos"));
        users.add(new User(6, "mango"));
    }

    @GetMapping("/")
    public String homePage() {
        return "hello guys from okten";
    }

    @GetMapping("/users")
    public List<User> getUsers() {

        return users;
    }


    // /user/2
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") int id) {
        List<User> collect = users
                .stream()
                .filter(user -> user.getId() == id)
                .collect(Collectors.toList());
        return collect.get(0);
    }


    @PostMapping("/users")
    public List<User> saveUser(@RequestBody User user) {
        System.out.println("work");
        System.out.println(user);
        users.add(user);
        return users;
    }
    // put
    //patch
    //delete

}
