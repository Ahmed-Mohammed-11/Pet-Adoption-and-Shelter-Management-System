package com.example.backend.Controller;

import com.example.backend.Service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/")
public class Test {

    private TestService testService;

    @GetMapping
    public String test(){
        return "Hello World";
    }
}
