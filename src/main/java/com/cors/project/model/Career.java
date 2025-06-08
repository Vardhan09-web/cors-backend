package com.cors.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="carrerdata") 
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String stream;

    @ElementCollection
    private List<String> requiredSubjects;

    @ElementCollection
    private List<String> keySkills;

    @ElementCollection
    private List<String> interests;

    @ElementCollection
    private List<String> personalityTypes;

    @ElementCollection
    private List<String> financialLevels;
    

}
