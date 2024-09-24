package com.example.OrderService.controller;

import com.example.OrderService.dto.ErrorResponseDto;
import com.example.OrderService.exception.InvalidOrderIdException;
import com.example.OrderService.service.OrderService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = {OrderController.class})
public class OrderServiceControllerAdvisor {

    @ExceptionHandler(InvalidOrderIdException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidOrderIdException(InvalidOrderIdException ex) {
        ErrorResponseDto responseDto = new ErrorResponseDto(ex.getMessage(),400);
        return new ResponseEntity<>(responseDto, HttpStatusCode.valueOf(400));
    }
}
