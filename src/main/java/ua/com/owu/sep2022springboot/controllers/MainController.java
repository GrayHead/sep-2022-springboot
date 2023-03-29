package ua.com.owu.sep2022springboot.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.sep2022springboot.dao.UserDAO;
import ua.com.owu.sep2022springboot.models.User;
import ua.com.owu.sep2022springboot.models.UserDTO;
import ua.com.owu.sep2022springboot.queryFilters.UserSpecifications;
import ua.com.owu.sep2022springboot.views.Views;

import java.util.List;

@RestController
@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/users")
public class MainController {

    private UserDAO userDAO;


    @PostMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody @Valid User user) {
        userDAO.save(user);

    }

    @GetMapping("")
    @JsonView(value = Views.Level1.class)
    public ResponseEntity<List<User>> getUsers() {
        List<User> all = userDAO.findAll(UserSpecifications.byId(4)
                .and(UserSpecifications.byAge(10))
                .and(UserSpecifications.byName("kokos"))
        );
        return new ResponseEntity<>(all, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public UserDTO getUsers(@PathVariable int id) {
        User user = userDAO.findById(id).get();
        UserDTO userDTO = new UserDTO(user);
        return userDTO;
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


}
