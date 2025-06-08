package com.cors.project.service;

import org.springframework.stereotype.Service;


import com.cors.project.model.Career;
import com.cors.project.repository.CareerRepo;

import java.util.List;

@Service
public class CareerService {

    private final CareerRepo repository;

    public CareerService(CareerRepo repository) {
        this.repository = repository;
    }

    public List<Career> getAllCareers() {
        return repository.findAll();
    }

    public void saveCareer(Career career) {
        repository.save(career);
    }

 public void updateCareer(Long id, Career career) {
    Career existingCareer = repository.findById(id).orElseThrow(() -> 
        new RuntimeException("Career not found with id: " + id));
    
    existingCareer.setTitle(career.getTitle());
    existingCareer.setStream(career.getStream());
    existingCareer.setRequiredSubjects(career.getRequiredSubjects());
    existingCareer.setKeySkills(career.getKeySkills());
    existingCareer.setInterests(career.getInterests());
    existingCareer.setPersonalityTypes(career.getPersonalityTypes());
    existingCareer.setFinancialLevels(career.getFinancialLevels());
    
    repository.save(existingCareer);
}

public void deleteCareer(Long id) {
    repository.deleteById(id);
}


}

