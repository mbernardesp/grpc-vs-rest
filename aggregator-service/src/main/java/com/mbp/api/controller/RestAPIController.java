package com.mbp.api.controller;

import com.mbp.api.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class RestAPIController {

    @Autowired
    private RestService service;

    @GetMapping("/unary/{number}")
    public Object getResponseUnary(@PathVariable int number){
        return this.service.getUnaryResponse(number);
    }

    @GetMapping("/multiplication/{number1}/{number2}")
    public Object getResponseMultiplication(@PathVariable int number1, @PathVariable int number2){
        return this.service.getMultiplicationResponse(number1, number2);
    }

}
