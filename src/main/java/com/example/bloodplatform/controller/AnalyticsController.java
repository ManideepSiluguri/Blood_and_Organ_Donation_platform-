package com.example.bloodplatform.controller;

import com.example.bloodplatform.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET})
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/overview")
    public ResponseEntity<Map<String, Object>> getOverview() {
        return ResponseEntity.ok(analyticsService.getOverallStats());
    }

    @GetMapping("/blood-distribution")
    public ResponseEntity<Map<String, Integer>> getBloodDistribution() {
        return ResponseEntity.ok(analyticsService.getBloodGroupDistribution());
    }

    @GetMapping("/organ-distribution")
    public ResponseEntity<Map<String, Integer>> getOrganDistribution() {
        return ResponseEntity.ok(analyticsService.getOrganTypeDistribution());
    }

    @GetMapping("/demand-prediction")
    public ResponseEntity<Map<LocalDate, Map<String, Object>>> getDemandPrediction() {
        return ResponseEntity.ok(analyticsService.predictDemandNext7Days());
    }

    @GetMapping("/emergency-stats")
    public ResponseEntity<Map<String, Object>> getEmergencyStats(@RequestParam String bloodGroup) {
        return ResponseEntity.ok(analyticsService.getEmergencyStats(bloodGroup));
    }

    @GetMapping("/critical-shortages")
    public ResponseEntity<List<Map<String, Object>>> getCriticalShortages() {
        return ResponseEntity.ok(analyticsService.getCriticalShortages());
    }

    @GetMapping("/donor-retention")
    public ResponseEntity<Map<String, Object>> getDonorRetention() {
        return ResponseEntity.ok(analyticsService.getDonorRetention());
    }
}
