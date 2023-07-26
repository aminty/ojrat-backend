package com.amin.ojrat.exception.global;

import com.amin.ojrat.exception.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionController {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        // Collect validation error messages
        List<String> errorMessages = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errorMessages.add(fieldError.getDefaultMessage());
        }

        DefaultResponse errorResponse = new DefaultResponse(-1, "Param Validation failed", errorMessages);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AdminCreationException.class)
    public ResponseEntity<DefaultResponse> handleUserCreationException(AdminCreationException ex) {
        DefaultResponse errorResponse = new DefaultResponse(-2, ex.getMessage(), new ArrayList<>());
        return new ResponseEntity<DefaultResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<DefaultResponse> userExistsByParams(UserExistsException ex) {
        DefaultResponse errorResponse = new DefaultResponse(-3, ex.getMessage(), new ArrayList<>());
        return new ResponseEntity<DefaultResponse>(errorResponse, HttpStatus.CONFLICT);

    }


    @ExceptionHandler(NotFullyRegistredException.class)
    public ResponseEntity<DefaultResponse> completeRegistration(NotFullyRegistredException ex) {
        DefaultResponse errorResponse = new DefaultResponse(-4, ex.getMessage(), new ArrayList<>());
        return new ResponseEntity<DefaultResponse>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DefaultResponse> entityNotFoundException(EntityNotFoundException ex) {
        DefaultResponse errorResponse = new DefaultResponse(-5, ex.getMessage(), new ArrayList<>());
        return new ResponseEntity<DefaultResponse>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingIdParameter.class)
    public ResponseEntity<DefaultResponse> missingIdParameterException(MissingIdParameter ex) {
        DefaultResponse errorResponse = new DefaultResponse(-6, ex.getMessage(), new ArrayList<>());
        return new ResponseEntity<DefaultResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

