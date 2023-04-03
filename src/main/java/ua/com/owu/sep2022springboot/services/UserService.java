package ua.com.owu.sep2022springboot.services;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.owu.sep2022springboot.dao.UserDAO;
import ua.com.owu.sep2022springboot.models.User;
import ua.com.owu.sep2022springboot.models.UserDTO;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserDAO userDAO;
    private MailService mailService;

    public void save(User user) {
        if (user == null) {
            throw new RuntimeException();
        }
        userDAO.save(user);
//        mailService.sendEmail(user);
    }


    public ResponseEntity<List<User>> findAllWithSpecifications(Specification<User> criteria) {
        List<User> all = userDAO.findAll(criteria);
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    public UserDTO getUser(int id) {
        UserDTO userDTO = null;
        if (id > 0) {
            userDTO = new UserDTO(userDAO.findById(id).get());
        }
        return userDTO;
    }

}


