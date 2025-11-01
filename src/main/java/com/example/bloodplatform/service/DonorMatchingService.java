package com.example.bloodplatform.service;

import com.example.bloodplatform.dto.BloodDonorResponseDto;
import com.example.bloodplatform.dto.OrganDonorResponseDto;
import com.example.bloodplatform.model.BloodDonor;
import com.example.bloodplatform.model.OrganDonor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DonorMatchingService {

    // Blood compatibility matrix
    private static final Map<String, List<String>> BLOOD_COMPATIBILITY = Map.ofEntries(
            Map.entry("O+", Arrays.asList("O+", "A+", "B+", "AB+")),
            Map.entry("O-", Arrays.asList("O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+")),
            Map.entry("A+", Arrays.asList("A+", "AB+")),
            Map.entry("A-", Arrays.asList("A-", "A+", "AB-", "AB+")),
            Map.entry("B+", Arrays.asList("B+", "AB+")),
            Map.entry("B-", Arrays.asList("B-", "B+", "AB-", "AB+")),
            Map.entry("AB+", Arrays.asList("AB+")),
            Map.entry("AB-", Arrays.asList("AB-", "AB+"))
    );

    /**
     * Find best matching blood donors for a recipient
     * Scoring: Blood compatibility (50%) + Distance (30%) + Recency (20%)
     */
    public List<BloodDonorResponseDto> matchBloodDonors(
            String recipientBloodGroup,
            String recipientCity,
            List<BloodDonor> availableDonors) {

        return availableDonors.stream()
                .map(donor -> {
                    double score = calculateBloodDonorScore(
                            recipientBloodGroup,
                            recipientCity,
                            donor
                    );
                    return new DonorMatch(donor, score);
                })
                .filter(match -> match.score > 0)
                .sorted((a, b) -> Double.compare(b.score, a.score))
                .limit(5)
                .map(match -> DonorMapper.toDto(match.donor))
                .collect(Collectors.toList());
    }

    /**
     * Calculate matching score for blood donor
     */
    private double calculateBloodDonorScore(String recipientBlood, String recipientCity, BloodDonor donor) {
        double score = 0;

        // 1. Blood Compatibility (50% weight)
        List<String> compatibleDonors = BLOOD_COMPATIBILITY.getOrDefault(recipientBlood, new ArrayList<>());
        double bloodScore = compatibleDonors.contains(donor.getBloodGroup()) ? 100 : 0;
        score += bloodScore * 0.5;

        // 2. Distance Score (30% weight)
        double distanceScore = calculateDistanceScore(recipientCity, donor.getAddress());
        score += distanceScore * 0.3;

        // 3. Recency Score (20% weight)
        double recencyScore = calculateRecencyScore(donor.getLastDonationDate());
        score += recencyScore * 0.2;

        return score;
    }

    /**
     * Calculate distance score (0-100, where 100 is same city)
     */
    private double calculateDistanceScore(String city1, String city2) {
        if (city1 == null || city2 == null) return 50;

        String c1 = city1.toLowerCase().split(",")[0].trim();
        String c2 = city2.toLowerCase().split(",")[0].trim();

        if (c1.equals(c2)) return 100;
        if (c1.contains(c2) || c2.contains(c1)) return 75;
        return 30; // Different city
    }

    /**
     * Calculate recency score (0-100, where 100 is donated today)
     */
    private double calculateRecencyScore(java.time.LocalDateTime lastDonation) {
        if (lastDonation == null) return 100; // Never donated = available

        long daysSinceDonation = java.time.temporal.ChronoUnit.DAYS
                .between(lastDonation, java.time.LocalDateTime.now());

        // Blood donation should wait 56 days minimum
        if (daysSinceDonation < 56) return 0; // Can't donate yet
        if (daysSinceDonation < 180) return 100; // Fresh donor
        if (daysSinceDonation < 365) return 80;
        return 60;
    }

    /**
     * Find best matching organ donors for recipient
     * Scoring: Organ type (40%) + Blood compatibility (30%) + Distance (20%) + Age compatibility (10%)
     */
    public List<OrganDonorResponseDto> matchOrganDonors(
            String organType,
            String recipientCity,
            String recipientBloodGroup,
            Integer recipientAge,
            List<OrganDonor> availableDonors) {

        return availableDonors.stream()
                .map(donor -> {
                    double score = calculateOrganDonorScore(
                            organType,
                            recipientCity,
                            recipientBloodGroup,
                            recipientAge,
                            donor
                    );
                    return new DonorMatch(donor, score);
                })
                .filter(match -> match.score > 0)
                .sorted((a, b) -> Double.compare(b.score, a.score))
                .limit(5)
                .map(match -> OrganDonationMapper.toDto((OrganDonor) match.donor))
                .collect(Collectors.toList());
    }

    /**
     * Calculate matching score for organ donor
     */
    private double calculateOrganDonorScore(
            String organType,
            String recipientCity,
            String recipientBlood,
            Integer recipientAge,
            OrganDonor donor) {

        double score = 0;

        // 1. Organ Type Match (40% weight)
        double organScore = organType.equalsIgnoreCase(donor.getOrganType()) ? 100 : 0;
        score += organScore * 0.4;

        // 2. Blood Compatibility (30% weight)
        List<String> compatibleDonors = BLOOD_COMPATIBILITY.getOrDefault(recipientBlood, new ArrayList<>());
        double bloodScore = compatibleDonors.contains(donor.getBloodGroup()) ? 100 : 50;
        score += bloodScore * 0.3;

        // 3. Distance Score (20% weight)
        double distanceScore = calculateDistanceScore(recipientCity, donor.getCity());
        score += distanceScore * 0.2;

        // 4. Age Compatibility (10% weight)
        double ageScore = calculateAgeCompatibilityScore(recipientAge, donor.getAge());
        score += ageScore * 0.1;

        return score;
    }

    /**
     * Calculate age compatibility (organs from younger donors are better)
     */
    private double calculateAgeCompatibilityScore(Integer recipientAge, Integer donorAge) {
        if (recipientAge == null || donorAge == null) return 50;

        int ageDifference = Math.abs(recipientAge - donorAge);

        if (ageDifference <= 10) return 100;
        if (ageDifference <= 20) return 85;
        if (ageDifference <= 30) return 70;
        return 50;
    }

    // Helper class for donor matching
    private static class DonorMatch {
        Object donor;
        double score;

        DonorMatch(Object donor, double score) {
            this.donor = donor;
            this.score = score;
        }
    }
}
