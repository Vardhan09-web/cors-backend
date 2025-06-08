package com.cors.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cors.project.model.Career;

@Repository
public interface CareerRepo extends JpaRepository<Career, Long> {
    List<Career> findAll();
}

