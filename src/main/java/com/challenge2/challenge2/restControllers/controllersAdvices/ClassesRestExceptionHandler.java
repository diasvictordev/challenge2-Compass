package com.challenge2.challenge2.restControllers.controllersAdvices;

import com.challenge2.challenge2.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.challenge2.challenge2.entities.ErrorResponse;
import com.challenge2.challenge2.exceptions.NotFoundException;

import java.sql.Timestamp;

@RestControllerAdvice
public class ClassesRestExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), new Timestamp(System.currentTimeMillis()),HttpStatus.NOT_FOUND.name());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globlalExceptionHandler(Exception ex, WebRequest request) {
        ErrorResponse error = new ErrorResponse("Something went wrong", new Timestamp(System.currentTimeMillis()),HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex){
        ErrorResponse error = new ErrorResponse(ex.getMessage(), new Timestamp(System.currentTimeMillis()),HttpStatus.BAD_REQUEST.name());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
