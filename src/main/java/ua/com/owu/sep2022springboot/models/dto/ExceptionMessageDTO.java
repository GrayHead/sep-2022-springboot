package ua.com.owu.sep2022springboot.models.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExceptionMessageDTO {
    private int statusCode;
    private String message;


}
