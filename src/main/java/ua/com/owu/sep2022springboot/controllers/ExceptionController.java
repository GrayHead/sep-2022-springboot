package ua.com.owu.sep2022springboot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.com.owu.sep2022springboot.models.dto.ExceptionMessageDTO;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionMessageDTO> handler(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ExceptionMessageDTO(400, e.getFieldError().getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionMessageDTO> handler(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(new ExceptionMessageDTO(400, e.getMessage()), HttpStatus.BAD_REQUEST);

    }
}
