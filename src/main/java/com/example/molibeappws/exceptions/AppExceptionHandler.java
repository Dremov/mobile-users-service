package com.example.molibeappws.exceptions;

import com.example.molibeappws.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception exception, WebRequest request) {
        var errorMessageDescribtion = exception.getLocalizedMessage();

        if (errorMessageDescribtion == null) errorMessageDescribtion = exception.toString();

        var errorMessage = ErrorMessage.builder()
                                            .timestamp(new Date())
                                            .message(errorMessageDescribtion)
                                            .build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(Exception exception, WebRequest request) {
        var errorMessageDescribtion = exception.getLocalizedMessage();

        if (errorMessageDescribtion == null) errorMessageDescribtion = exception.toString();

        var errorMessage = ErrorMessage.builder()
                .timestamp(new Date())
                .message(errorMessageDescribtion)
                .build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
