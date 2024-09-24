package com.example.UserService.controller;

import com.example.UserService.dto.ErrorMessage;
import com.example.UserService.exception.CustomeException;
import com.example.UserService.exception.InvalidPasswordException;
import com.example.UserService.exception.UserAlreadyExist;
import com.example.UserService.exception.UserNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = {AuthController.class,RoleController.class, UserController.class})
public class CustomeExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException ex){
        ErrorMessage errorMessage = new ErrorMessage(
                ex.getMessage(),
                404
        );
        return  new ResponseEntity<>(errorMessage, HttpStatusCode.valueOf(404));
    }
    @ExceptionHandler({UserAlreadyExist.class, InvalidPasswordException.class})
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(CustomeException ex){
        ErrorMessage errorMessage = new ErrorMessage(
                ex.getMessage(),
                400
        );
        return  new ResponseEntity<>(errorMessage, HttpStatusCode.valueOf(400));
    }
}
