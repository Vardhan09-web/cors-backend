package com.cors.project.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {



    @PostMapping("/add-career")
    public ResponseEntity<String> addCareer(@RequestBody String dto) {
        return ResponseEntity.ok("Career added successfully");
    }
}