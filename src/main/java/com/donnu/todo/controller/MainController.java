package com.donnu.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public ResponseEntity inicial(){
        return ResponseEntity.ok().body("Привет");
    }
}
