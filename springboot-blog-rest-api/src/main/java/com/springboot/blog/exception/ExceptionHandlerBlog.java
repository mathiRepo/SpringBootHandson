package com.springboot.blog.exception;

import com.springboot.blog.payload.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerBlog extends ResponseEntityExceptionHandler {


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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().stream().forEach(
                (error) -> {
                    String field = ((FieldError)error).getField();
                    String msg = error.getDefaultMessage();
                    errors.put(field,msg);
                }
        );
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
//                                                                  WebRequest request) {
//        Map<String,String> errors = new HashMap<>();
//        ex.getBindingResult().getFieldErrors().stream().forEach(
//                (error) -> {
//                    String field = ((FieldError)error).getField();
//                    String msg = error.getDefaultMessage();
//                    errors.put(field,msg);
//                }
//        );
//        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
//    }

}
