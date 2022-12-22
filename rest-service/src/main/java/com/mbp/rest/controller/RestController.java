package com.mbp.rest.controller;

import com.mbp.rest.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private RestService restService;

    @GetMapping("/rest/unary/{number}")
    public String getUnary(@PathVariable int number){
        return this.restService.getUnary(number);
    }

}
