package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public BlogApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        System.out.println("From Normal Constructor");
    }

//    public BlogApiException(String message, HttpStatus status, String message1) {
//        super(message);
//        this.status = status;
//        this.message = message1;
//        System.out.println("From super Constructor");
//        System.out.println(message1);
//    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
