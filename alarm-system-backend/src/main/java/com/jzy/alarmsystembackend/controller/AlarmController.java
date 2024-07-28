package com.jzy.alarmsystembackend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlarmController {

    @PostMapping("/test")
    public String test() {
        return "test";
    }
}
