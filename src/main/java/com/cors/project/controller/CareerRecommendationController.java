// package com.cors.project.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.cors.project.dto.CareerRecommendationResponse;
// import com.cors.project.dto.UserCareerPreferenceRequest;
// import com.cors.project.service.CareerRecommendationService;

// @RestController
// @RequestMapping("/api/careers")
// public class CareerRecommendationController {

//     @Autowired
//     private CareerRecommendationService recommendationService;

//     @PostMapping("/recommend")
//     public ResponseEntity<List<CareerRecommendationResponse>> recommendCareer(
//             @RequestBody UserCareerPreferenceRequest request) {
//         List<CareerRecommendationResponse> recommendations = recommendationService.recommendCareers(request);
//         return ResponseEntity.ok(recommendations);
//     }
// }

package com.cors.project.controller;

import com.cors.project.dto.CareerRecommendationResponse;
import com.cors.project.dto.UserCareerPreferenceRequest;
import com.cors.project.service.CareerRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/careers")
public class CareerRecommendationController {

    @Autowired
    private CareerRecommendationService recommendationService;

    @PostMapping("/recommend")
    // @PreAuthorize("isAuthenticated()") // Optional: "hasRole('USER')" if roles are set
    public ResponseEntity<List<CareerRecommendationResponse>> recommendCareer(
            @RequestBody UserCareerPreferenceRequest request) {
        List<CareerRecommendationResponse> recommendations = recommendationService.recommendCareers(request);
        return ResponseEntity.ok(recommendations);
    }
}

