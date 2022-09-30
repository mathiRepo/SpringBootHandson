package net.javaguides.springbootrestapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
//RestController contains @Controller(Used to represent the calss as a contoller to get het the http request from dispatcher servler) and
// @ResponseBody(Used to deserialize the object to json and vise versa)
public class HelloWorldController {

    @GetMapping("getHello")
    public HashMap<String,String> helloWorld(){

        HashMap<String,String> json = new HashMap<>();
        json.put("Note","Hello Mathi I am from Local Host!!! ALL the best for the Handson");
        json.put("","");
        System.out.println("Incoming Hit from Postman");
        return json;
    }
}
