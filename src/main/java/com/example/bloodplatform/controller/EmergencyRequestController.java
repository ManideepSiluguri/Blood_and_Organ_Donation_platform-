package com.example.bloodplatform.controller;

import com.example.bloodplatform.dto.EmergencyRequestDto;
import com.example.bloodplatform.dto.EmergencyResponseDto;
import com.example.bloodplatform.mapper.EmergencyRequestMapper;
import com.example.bloodplatform.service.EmergencyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
@RequestMapping("/api/emergencies")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class EmergencyRequestController {
    private final EmergencyService emergencyService;
    private final NotificationController notificationController;

    @Autowired
    public EmergencyRequestController(EmergencyService emergencyService, NotificationController notificationController) {
        this.emergencyService = emergencyService;
        this.notificationController = notificationController;
    }

    @PostMapping
    public ResponseEntity<EmergencyResponseDto> create(@Valid @RequestBody EmergencyRequestDto req) {
        var emergency = emergencyService.create(EmergencyRequestMapper.fromDto(req));

        // Send real-time notification
        notificationController.broadcastToBloodGroup(
                req.getBloodGroupNeeded(),
                "🚨 Emergency Blood Request",
                "Patient needs " + req.getBloodGroupNeeded() + " blood urgently at " + req.getLocation()
        );

        return ResponseEntity.ok(EmergencyRequestMapper.toDto(emergency));
    }

    @PostMapping("/{id}/claim")
    public ResponseEntity<EmergencyResponseDto> claim(@PathVariable UUID id, @RequestParam String responder) {
        var emergency = emergencyService.claim(id, responder);

        // Send notification that request was claimed
        notificationController.emergencyBroadcast(
                emergency.getLocation(),
                "✅ Emergency Responded",
                "Someone nearby is on the way to help!"
        );

        return ResponseEntity.ok(EmergencyRequestMapper.toDto(emergency));
    }

    // ... rest of controller
}
