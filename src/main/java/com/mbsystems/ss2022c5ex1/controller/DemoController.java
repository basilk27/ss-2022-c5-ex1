package com.mbsystems.ss2022c5ex1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping(path = "/demo")
    public String demo() {
        return "Demo";
    }

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello";
    }
}
