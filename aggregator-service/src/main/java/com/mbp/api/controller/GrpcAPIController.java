package com.mbp.api.controller;

import com.mbp.api.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("grpc")
public class GrpcAPIController {

    @Autowired
    private GrpcService service;

    @GetMapping("/unary/{number}")
    public Object getResponseUnary(@PathVariable int number){

        return this.service.getResponseUnary(number);
    }

    @GetMapping("/stream/{number}")
    public Object getResponseStream(@PathVariable int number){
        return this.service.getResponseStream(number);
    }

}
