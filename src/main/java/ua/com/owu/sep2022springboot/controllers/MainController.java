package ua.com.owu.sep2022springboot.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.sep2022springboot.dao.UserDAO;
import ua.com.owu.sep2022springboot.models.User;

import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")

public class MainController {

    private UserDAO userDAO;


    @PostMapping("/users")
    public void save(@RequestBody User user) {

        userDAO.save(user);
        return;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> all = userDAO.findAll();
        return all;
    }


    @GetMapping("/users/{id}")
    public User getUsers(@PathVariable int id) {
        User user = userDAO.findById(id).get();
        return user;
    }

    @DeleteMapping("/users/{id}")
    public List<User> deleteUser(@PathVariable int id) {
        userDAO.deleteById(id);
        return userDAO.findAll();
    }


    @PatchMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        User u = userDAO.findById(id).get();
        u.setName(user.getName());
        userDAO.save(u);
        return u;
    }

    @GetMapping("/users/name/{nameValue}")
    public List<User> usersBuNameLength(@PathVariable String nameValue) {
//        List<User> usersByNameLength = userDAO.getUsersByNameLength(nameValue);
//        return usersByNameLength;

        return userDAO.findByName(nameValue);


    }

    @DeleteMapping("/users/all/{id}")
    public void deleteAllByName(@PathVariable int id) {
        userDAO.deleteById(id);
    }


}
