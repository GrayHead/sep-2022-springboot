package ua.com.owu.sep2022springboot.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ClientUserDTO {
    private String username;
    private String password;
}
