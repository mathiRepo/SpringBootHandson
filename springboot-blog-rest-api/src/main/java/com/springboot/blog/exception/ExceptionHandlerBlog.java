package com.springboot.blog.exception;

import com.springboot.blog.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandlerBlog {


    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFound exception,
            WebRequest webRequest
    ){
      ErrorResponse errorResponse = new ErrorResponse(
        new Date(),
        exception.getMessage(),
        webRequest.getDescription(false)
      );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogApiException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            BlogApiException exception,
            WebRequest webRequest
    ){
        ErrorResponse errorResponse = new ErrorResponse(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            Exception exception,
            WebRequest webRequest
    ){
        ErrorResponse errorResponse = new ErrorResponse(
                new Date(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
