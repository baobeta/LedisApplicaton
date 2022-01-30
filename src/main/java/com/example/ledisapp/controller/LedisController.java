package com.example.ledisapp.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LedisController {

    @GetMapping("/")
    public String viewIndexPage() {
        return "index";
    }

    @PostMapping("/")
    public String sendReturnResult( @Param("key") String key) {
        String keyword = key;
        return "index";
    }
}
