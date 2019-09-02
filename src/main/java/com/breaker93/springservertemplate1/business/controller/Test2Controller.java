package com.breaker93.springservertemplate1.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/abc")
public class Test2Controller {
    @GetMapping
    public String get(){
        return "I'm xyj.";
    }

    @PostMapping
    public String post(String name){
        return "You are " + name;
    }
}
