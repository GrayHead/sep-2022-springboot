package ua.com.owu.sep2022springboot.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.sep2022springboot.dao.UserDAO;
import ua.com.owu.sep2022springboot.dao.mongo.ClientMongoDAO;
import ua.com.owu.sep2022springboot.models.User;
import ua.com.owu.sep2022springboot.models.UserDTO;
import ua.com.owu.sep2022springboot.models.mongomodels.Client;
import ua.com.owu.sep2022springboot.queryFilters.UserSpecifications;
import ua.com.owu.sep2022springboot.services.UserService;
import ua.com.owu.sep2022springboot.views.Views;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/users")
public class MainController {

    // TODO: 03.04.2023 Delete userdao after replace by userservice
    private UserDAO userDAO;

    private UserService userService;
    private ClientMongoDAO clientMongoDAO;

    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody @Valid User user) {
        userService.save(user);
        clientMongoDAO.save(new Client(user.getName(), user.getAge()));
    }

    @GetMapping("")
    @JsonView(value = Views.Level1.class)
    public ResponseEntity<List<User>> getUsers() {
        return userService.findAllWithSpecifications(UserSpecifications.byId(4)
                .and(UserSpecifications.byAge(10))
                .and(UserSpecifications.byName("kokos")));
    }


    @GetMapping("/{id}")
    public UserDTO getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public List<User> deleteUser(@PathVariable int id) {
        userDAO.deleteById(id);
        return userDAO.findAll();
    }


    @PatchMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        User u = userDAO.findById(id).get();
        u.setName(user.getName());
        userDAO.save(u);
        return u;
    }

    @GetMapping("/name/{nameValue}")
    @JsonView(value = Views.Level2.class)
    public List<User> usersBuNameLength(@PathVariable String nameValue) {
//        List<User> usersByNameLength = userDAO.getUsersByNameLength(nameValue);
//        return usersByNameLength;

        return userDAO.findByName(nameValue);


    }

    @DeleteMapping("/all/{name}")
    public void deleteAllByName(@PathVariable String name) {
        userDAO.deleteAllByName(name);
    }


    @PostMapping("/saveWithAvatar")
    public void saveWithAvatar(
            @RequestParam String name,
            @RequestParam int age,
            @RequestParam MultipartFile avatar
    ) throws IOException {

        User user = new User(name, age);
        String originalFilename = avatar.getOriginalFilename();
        user.setAvatar("/photo/" + originalFilename);
        String path = System.getProperty("user.home") + File.separator + "images" + File.separator + originalFilename;
        File file = new File(path);
        avatar.transferTo(file);
        userService.save(user);
    }

}
