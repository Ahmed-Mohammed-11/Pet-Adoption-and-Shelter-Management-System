package com.example.backend.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/adopter/")
public class AdopterController {

    @PostMapping("test")
    public String test(@AuthenticationPrincipal String s) {
        return "test";
    }
}