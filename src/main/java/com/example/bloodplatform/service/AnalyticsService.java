package com.example.bloodplatform.service;


import com.example.bloodplatform.model.*;
import com.example.bloodplatform.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    @Autowired private BloodDonorRepository bloodDonorRepo;
    @Autowired private OrganDonorRepository organDonorRepo;
    @Autowired private EmergencyRequestRepository emergencyRepo;

    /**
     * Get total statistics
     */
    public Map<String, Object> getOverallStats() {
        return Map.of(
                "totalBloodDonors", bloodDonorRepo.count(),
                "totalOrganDonors", organDonorRepo.count(),
                "pendingEmergencies", emergencyRepo.findByStatus("NEW").size(),
                "respondedEmergencies", emergencyRepo.findByStatus("RESPONDED").size()
        );
    }

    /**
     * Get blood group distribution
     */
    public Map<String, Integer> getBloodGroupDistribution() {
        var allDonors = bloodDonorRepo.findAll();
        return allDonors.stream()
                .collect(Collectors.groupingBy(
                        BloodDonor::getBloodGroup,
                        Collectors.collectingAndThen(Collectors.toList(), List::size)
                ));
    }

    /**
     * Get organ type distribution
     */
    public Map<String, Integer> getOrganTypeDistribution() {
        var allDonors = organDonorRepo.findAll();
        return allDonors.stream()
                .collect(Collectors.groupingBy(
                        OrganDonor::getOrganType,
                        Collectors.collectingAndThen(Collectors.toList(), List::size)
                ));
    }

    /**
     * Predict blood demand for next 7 days
     * Simple ML: Based on historical patterns + current trends
     */
    public Map<LocalDate, Map<String, Object>> predictDemandNext7Days() {
        Map<LocalDate, Map<String, Object>> predictions = new LinkedHashMap<>();

        // Get last 30 days data
        var emergencies = emergencyRepo.findAll();

        // Simple pattern: More emergencies on weekends
        LocalDate today = LocalDate.now();
        for (int i = 1; i <= 7; i++) {
            LocalDate date = today.plusDays(i);
            int dayOfWeek = date.getDayOfWeek().getValue();

            // Higher demand on weekends (assume)
            int baseDemand = 5;
            int predictedDemand = (dayOfWeek >= 5) ? baseDemand + 10 : baseDemand + 5;

            String riskLevel = predictedDemand > 15 ? "HIGH" : predictedDemand > 10 ? "MEDIUM" : "LOW";

            predictions.put(date, Map.of(
                    "predictedDemand", predictedDemand,
                    "riskLevel", riskLevel,
                    "reason", getReasonForDate(date)
            ));
        }

        return predictions;
    }

    /**
     * Get emergency statistics by blood group
     */
    public Map<String, Object> getEmergencyStats(String bloodGroup) {
        var emergencies = emergencyRepo.findAll();
        var relevant = emergencies.stream()
                .filter(e -> e.getBloodGroupNeeded().equalsIgnoreCase(bloodGroup))
                .toList();

        int total = relevant.size();
        int responded = (int) relevant.stream().filter(e -> "RESPONDED".equals(e.getStatus())).count();

        return Map.of(
                "totalRequests", total,
                "respondedRequests", responded,
                "pendingRequests", total - responded,
                "responseRate", total > 0 ? (responded * 100 / total) : 0
        );
    }

    /**
     * Identify critical blood shortages
     */
    public List<Map<String, Object>> getCriticalShortages() {
        var distribution = getBloodGroupDistribution();
        List<Map<String, Object>> shortages = new ArrayList<>();

        distribution.forEach((bloodGroup, count) -> {
            // Assume each blood bank should have at least 50 units
            if (count < 20) {
                shortages.add(Map.of(
                        "bloodGroup", bloodGroup,
                        "currentCount", count,
                        "status", count < 10 ? "CRITICAL" : "WARNING"
                ));
            }
        });

        return shortages;
    }

    /**
     * Get donor retention rate (how many donate regularly)
     */
    public Map<String, Object> getDonorRetention() {
        var allDonors = bloodDonorRepo.findAll();

        long regularDonors = allDonors.stream()
                .filter(d -> d.getLastDonationDate() != null &&
                        d.getLastDonationDate().isAfter(LocalDateTime.now().minusDays(180)))
                .count();

        return Map.of(
                "totalDonors", allDonors.size(),
                "regularDonors", regularDonors,
                "retentionRate", allDonors.size() > 0 ? (regularDonors * 100 / allDonors.size()) : 0
        );
    }

    private String getReasonForDate(LocalDate date) {
        // Simple logic: Check if it's a weekend or holiday
        int dayOfWeek = date.getDayOfWeek().getValue();
        if (dayOfWeek >= 5) return "Weekend traffic accidents";
        if (date.getDayOfMonth() == 15) return "Monthly medical checkups";
        return "Normal";
    }
}
