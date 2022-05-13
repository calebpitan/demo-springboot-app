package com.example.demo.api;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello, world!";
    }

    @GetMapping("/hello")
    public String greeting(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }
}
