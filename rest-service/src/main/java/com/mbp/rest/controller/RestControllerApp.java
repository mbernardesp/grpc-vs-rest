package com.mbp.rest.controller;

import com.mbp.rest.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RestControllerApp {

    @Autowired
    private RestService restService;

    @GetMapping("/rest/unary/{number}")
    public String getUnary(@PathVariable int number){
        return this.restService.getUnary(number);
    }

    @GetMapping("/rest/multiplication/{number1}/{number2}")
    public int getMultiplication(@PathVariable int number1, @PathVariable int number2){
        return this.restService.getMultiplication(number1, number2);
    }
}
