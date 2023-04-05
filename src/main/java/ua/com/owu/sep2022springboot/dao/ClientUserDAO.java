package ua.com.owu.sep2022springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.sep2022springboot.models.ClientUser;

public interface ClientUserDAO extends JpaRepository<ClientUser, Integer> {

    ClientUser findByEmail(String email);


}
