package com.amin.ojrat.exception.global;

import com.amin.ojrat.exception.*;
import jakarta.persistence.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.relation.RelationNotFoundException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionController {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> errorMessages = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            errorMessages.add(fieldError.getDefaultMessage());
        }

        DefaultResponse errorResponse = new DefaultResponse(-1, "Param Validation failed", errorMessages);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CreationException.class)
    public ResponseEntity<DefaultResponse> handleUserCreationException(CreationException ex) {
        DefaultResponse errorResponse = new DefaultResponse(-2, ex.getMessage(), new ArrayList<>());
        return new ResponseEntity<DefaultResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<DefaultResponse> userExistsByParams(UserExistsException ex) {
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add("phoneNumber");
        errorMessages.add("nationalCode");
        errorMessages.add("email");
        DefaultResponse errorResponse = new DefaultResponse(-3, ex.getMessage(), errorMessages);
        return new ResponseEntity<DefaultResponse>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotFullyRegisteredException.class)
    public ResponseEntity<DefaultResponse> completeRegistration(NotFullyRegisteredException ex) {
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

    @ExceptionHandler(LoginAuthenticationException.class)
    public ResponseEntity<DefaultResponse> authenticationException(LoginAuthenticationException ex) {
        DefaultResponse errorResponse = new DefaultResponse(-7, ex.getMessage(), new ArrayList<>());
        return new ResponseEntity<DefaultResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TtlExpirationException.class)
    public ResponseEntity<DefaultResponse> ttlExpirationException(TtlExpirationException ex){
        DefaultResponse errorResponse = new DefaultResponse(-8,ex.getMessage(),new ArrayList<>());
        return new ResponseEntity<>(errorResponse,HttpStatus.SERVICE_UNAVAILABLE);

    }
    @ExceptionHandler(RequestLimitExceededException.class)
    public ResponseEntity<DefaultResponse> requestLimitException(RequestLimitExceededException ex){
        DefaultResponse errorResponse = new DefaultResponse(-9,ex.getMessage(),new ArrayList<>());
        return new ResponseEntity<>(errorResponse,HttpStatus.TOO_MANY_REQUESTS);

    }
    @ExceptionHandler(LicenseStatusException.class)
    public ResponseEntity<DefaultResponse> licenseStatusException(LicenseStatusException ex){
        DefaultResponse errorResponse = new DefaultResponse(-10,ex.getMessage(),new ArrayList<>());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_ACCEPTABLE);

    }
    @ExceptionHandler(ActivationException.class)
    public ResponseEntity<DefaultResponse> activationException(ActivationException ex){
        DefaultResponse errorResponse = new DefaultResponse(-11,ex.getMessage(),new ArrayList<>());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(UniqueNameException.class)
    public ResponseEntity<DefaultResponse> activationException(UniqueNameException ex){
        DefaultResponse errorResponse = new DefaultResponse(-12,ex.getMessage(),new ArrayList<>());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DeletionException.class)
    public ResponseEntity<DefaultResponse> activationException(DeletionException ex){
        DefaultResponse errorResponse = new DefaultResponse(-13,ex.getMessage(),new ArrayList<>());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ChangeStatusException.class)
    public ResponseEntity<DefaultResponse> activationException(ChangeStatusException ex){
        DefaultResponse errorResponse = new DefaultResponse(-14,ex.getMessage(),new ArrayList<>());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(RelationNotFoundException.class)
    public ResponseEntity<DefaultResponse> activationException(RelationNotFoundException ex){
        DefaultResponse errorResponse = new DefaultResponse(-15,ex.getMessage(),new ArrayList<>());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<DefaultResponse> permissionDeniedException(PermissionDeniedException ex){
        DefaultResponse errorResponse = new DefaultResponse(-16,ex.getMessage(),new ArrayList<>());
        return new ResponseEntity<>(errorResponse,HttpStatus.FORBIDDEN);
    }

}

