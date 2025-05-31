package com.cors.project.dto;

import lombok.Data;
import java.util.List;

@Data
public class UserCareerPreferenceRequest {
    private List<String> selectedSubjects;
    private List<String> selectedSkills;
    private List<String> selectedInterests;
    private List<String> selectedPersonalityTypes;
    private List<String> selectedFinancialLevels;
}

