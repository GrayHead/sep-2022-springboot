package ua.com.owu.sep2022springboot.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.owu.sep2022springboot.dao.ClientUserDAO;
import ua.com.owu.sep2022springboot.models.ClientUser;

@Service
@AllArgsConstructor
public class ClientUserService implements UserDetailsService {
    private ClientUserDAO clientUserDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        ClientUser byEmail = clientUserDAO.findByEmail(username);
        System.out.println(byEmail);
        return byEmail;
    }
}
