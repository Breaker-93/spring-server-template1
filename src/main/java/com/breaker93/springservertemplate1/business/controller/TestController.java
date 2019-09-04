package com.breaker93.springservertemplate1.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public String get(){
        if (true) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return "Hello,Tianjin!";
    }

    @PostMapping
    public String post(String name){
        return "Welcome to " + name;
    }
}
