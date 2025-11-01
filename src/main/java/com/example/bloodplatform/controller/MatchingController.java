package com.example.bloodplatform.controller;

import com.example.bloodplatform.dto.BloodDonorResponseDto;
import com.example.bloodplatform.dto.OrganDonorResponseDto;
import com.example.bloodplatform.service.BloodDonorService;
import com.example.bloodplatform.service.OrganDonorService;
import com.example.bloodplatform.service.DonorMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matching")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class MatchingController {

    @Autowired
    private DonorMatchingService matchingService;

    @Autowired
    private BloodDonorService bloodDonorService;

    @Autowired
    private OrganDonorService organDonorService;

    /**
     * Find best matched blood donors for a recipient
     */
    @PostMapping("/blood-donors")
    public ResponseEntity<List<BloodDonorResponseDto>> matchBloodDonors(
            @RequestParam String bloodGroup,
            @RequestParam String city) {

        var allDonors = bloodDonorService.findAll();
        var matches = matchingService.matchBloodDonors(bloodGroup, city, allDonors);
        return ResponseEntity.ok(matches);
    }

    /**
     * Find best matched organ donors for a recipient
     */
    @PostMapping("/organ-donors")
    public ResponseEntity<List<OrganDonorResponseDto>> matchOrganDonors(
            @RequestParam String organType,
            @RequestParam String city,
            @RequestParam String bloodGroup,
            @RequestParam Integer age) {

        var allDonors = organDonorService.findAll();
        var matches = matchingService.matchOrganDonors(organType, city, bloodGroup, age, allDonors);
        return ResponseEntity.ok(matches);
    }
}
