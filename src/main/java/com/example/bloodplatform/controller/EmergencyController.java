package com.example.bloodplatform.controller;

import com.example.bloodplatform.model.EmergencyRequest;
import com.example.bloodplatform.service.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/emergencies")
public class EmergencyController {

    private final EmergencyService emergencyService;

    @Autowired
    public EmergencyController(EmergencyService emergencyService) { this.emergencyService = emergencyService; }

    @PostMapping
    public ResponseEntity<EmergencyRequest> create(@RequestBody EmergencyRequest request) { return ResponseEntity.ok(emergencyService.create(request)); }

    @PostMapping("/{id}/claim")
    public ResponseEntity<EmergencyRequest> claim(@PathVariable UUID id, @RequestParam String responder) { return ResponseEntity.ok(emergencyService.claim(id, responder)); }

    @GetMapping
    public ResponseEntity<List<EmergencyRequest>> list(@RequestParam(required=false) String status) {
        if (status == null) status = "NEW";
        return ResponseEntity.ok(emergencyService.listByStatus(status));
    }
}
