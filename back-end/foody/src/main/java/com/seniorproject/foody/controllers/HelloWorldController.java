package com.seniorproject.foody.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("http://localhost:3300")//react is at 3300
@RestController
@RequestMapping("/api/hello")
public class HelloWorldController {
    @GetMapping("/")
    public String hello(){
        return "hello world";
    }
}
