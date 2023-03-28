package ua.com.owu.sep2022springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.owu.sep2022springboot.models.User;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.name =:name ")
    List<User> getUsersByNameLength(@Param("name") String name);


    List<User> findByName(String name);

    void deleteById(int id);

    @Modifying
    void deleteAllByName(@Param("name") String value);


}
