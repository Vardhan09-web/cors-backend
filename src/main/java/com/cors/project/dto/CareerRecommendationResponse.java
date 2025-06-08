package com.cors.project.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareerRecommendationResponse {
    private String title;
    private String stream;
    private int score;
    private String reason;
}
