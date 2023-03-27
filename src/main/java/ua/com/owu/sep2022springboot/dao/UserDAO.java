package ua.com.owu.sep2022springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.sep2022springboot.models.User;

public interface UserDAO extends JpaRepository<User, Integer> {
}
