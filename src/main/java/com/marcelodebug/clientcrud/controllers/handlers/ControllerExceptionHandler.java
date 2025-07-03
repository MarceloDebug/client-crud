package com.marcelodebug.clientcrud.controllers.handlers;

import com.marcelodebug.clientcrud.dto.CustomError;
import com.marcelodebug.clientcrud.dto.ValidationError;
import com.marcelodebug.clientcrud.services.exceptions.DatabaseException;
import com.marcelodebug.clientcrud.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.zip.DataFormatException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError error = new CustomError(Instant.now(),status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataFormatException.class)
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError customError = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(customError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError validationError = new ValidationError(Instant.now(), status.value(),"Erro de validação" , request.getRequestURI());

        for(FieldError f : e.getBindingResult().getFieldErrors()){
            validationError.add(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(status).body(validationError);
    }
}
