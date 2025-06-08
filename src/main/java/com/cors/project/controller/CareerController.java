package com.cors.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cors.project.model.Career;
import com.cors.project.service.CareerService;

import java.util.List;

@RestController
@RequestMapping("/api/careers")
public class CareerController {

    private final CareerService careerService;

    public CareerController(CareerService careerService) {
        this.careerService = careerService;
    }

    @GetMapping
    public List<Career> getAllCareers() {
        return careerService.getAllCareers();
    }

    @PostMapping
    public String addCareer(@RequestBody Career career) {
        careerService.saveCareer(career);
        return "Career added successfully";
    }

    @PostMapping("/upload")
public ResponseEntity<String> uploadCareers(@RequestBody List<Career> careers) {
    for (Career career : careers) {
        careerService.saveCareer(career);
    }
    return ResponseEntity.ok("Careers uploaded successfully!");
}
@PutMapping("/{id}")
public ResponseEntity<String> updateCareer(@PathVariable Long id, @RequestBody Career career) {
    careerService.updateCareer(id, career);
    return ResponseEntity.ok("Career updated successfully");
}

@DeleteMapping("/{id}")
public ResponseEntity<String> deleteCareer(@PathVariable Long id) {
    careerService.deleteCareer(id);
    return ResponseEntity.ok("Career deleted successfully");
}

}

