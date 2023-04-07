package ua.com.owu.sep2022springboot.controllers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.sep2022springboot.dao.ClientUserDAO;
import ua.com.owu.sep2022springboot.models.ClientUser;
import ua.com.owu.sep2022springboot.models.dto.ClientUserDTO;
import ua.com.owu.sep2022springboot.services.ClientUserService;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class MainController {

    private ClientUserDAO clientUserDAO;
    private PasswordEncoder passwordEncoder;
    private ClientUserService clientUserService;

    @PostMapping("/clients/save")
    public void saveClient(@RequestBody ClientUserDTO clientUserDTO) {

//        clientUserDAO.save(ClientUser.builder()
//                .email(clientUserDTO.getUsername())
//                .password(passwordEncoder.encode(clientUserDTO.getPassword()))
//                .build());

        ClientUser clientUser = new ClientUser();
        clientUser.setEmail(clientUserDTO.getUsername());
        clientUser.setPassword(passwordEncoder.encode(clientUserDTO.getPassword()));

        clientUserDAO.save(clientUser);
    }

    @PostMapping("/clients/login")
    public ResponseEntity<String> login(@RequestBody ClientUserDTO clientUserDTO) {
        return clientUserService.login(clientUserDTO);

    }


    //OPEN
    @GetMapping("/clients/all")
    public List<String> getAllClientsWithoutSensetiveInformation() {
        return clientUserDAO
                .findAll()
                .stream()
                .map(clientUser -> clientUser.getEmail())
                .collect(Collectors.toList());

    }

    //ADMIN
    @GetMapping("/admin/all")
    public List<ClientUser> getAllClients() {
        return clientUserDAO.findAll();
    }

}
