package com.cors.project.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cors.project.dto.CareerRecommendationResponse;
import com.cors.project.dto.UserCareerPreferenceRequest;
import com.cors.project.model.Career;
import com.cors.project.repository.CareerRepo;

@Service
public class CareerRecommendationService {

    @Autowired
    private CareerRepo careerDataRepository;

public List<CareerRecommendationResponse> recommendCareers(UserCareerPreferenceRequest request) {
    List<Career> allCareers = careerDataRepository.findAll();
    List<CareerRecommendationResponse> scoredCareers = new ArrayList<>();

    for (Career career : allCareers) {
        int score = 0;
        List<String> reasons = new ArrayList<>();

        List<String> matchedSubjects = career.getRequiredSubjects().stream()
            .filter(request.getSelectedSubjects()::contains).toList();
        if (!matchedSubjects.isEmpty()) {
            reasons.add("your interest in " + String.join(", ", matchedSubjects));
            score += matchedSubjects.size() * 2;
        }

        List<String> matchedSkills = career.getKeySkills().stream()
            .filter(request.getSelectedSkills()::contains).toList();
        if (!matchedSkills.isEmpty()) {
            reasons.add("your skills in " + String.join(", ", matchedSkills));
            score += matchedSkills.size() * 2;
        }

        List<String> matchedInterests = career.getInterests().stream()
            .filter(request.getSelectedInterests()::contains).toList();
        if (!matchedInterests.isEmpty()) {
            reasons.add("your passion for " + String.join(", ", matchedInterests));
            score += matchedInterests.size() * 2;
        }

        List<String> matchedPTs = career.getPersonalityTypes().stream()
            .filter(request.getSelectedPersonalityTypes()::contains).toList();
        if (!matchedPTs.isEmpty()) {
            reasons.add("your personality traits like " + String.join(", ", matchedPTs));
            score += matchedPTs.size();
        }

        List<String> matchedFLs = career.getFinancialLevels().stream()
            .filter(request.getSelectedFinancialLevels()::contains).toList();
        if (!matchedFLs.isEmpty()) {
            reasons.add("your preferred financial background of " + String.join(", ", matchedFLs));
            score += matchedFLs.size();
        }

        if (score > 0) {
            String recommendationLevel;

if (score >= 12) {
    recommendationLevel = "Highly Recommended: You are an excellent fit for this career.";
} else if (score >= 7) {
    recommendationLevel = "Recommended: You have a good potential to succeed in this career.";
} else {
    recommendationLevel = "Worth Exploring: This career matches some of your interests and skills.";
}

String reason = recommendationLevel + " This is because of " + String.join(", ", reasons) + 
    ". Based on your profile, you may find success and fulfillment in this field.";

            scoredCareers.add(new CareerRecommendationResponse(
                career.getTitle(),
                career.getStream(),
                score,
                reason
            ));
        }
    }

    return scoredCareers.stream()
            .sorted(Comparator.comparingInt(CareerRecommendationResponse::getScore).reversed())
            .limit(3)
            .collect(Collectors.toList());
}


}
