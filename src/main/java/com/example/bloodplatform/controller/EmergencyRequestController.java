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

    @Autowired
    public EmergencyRequestController(EmergencyService emergencyService) {
        this.emergencyService = emergencyService;
    }

    @PostMapping
    public ResponseEntity<EmergencyResponseDto> create(@Valid @RequestBody EmergencyRequestDto req) {
        var emergency = emergencyService.create(EmergencyRequestMapper.fromDto(req));
        return ResponseEntity.ok(EmergencyRequestMapper.toDto(emergency));
    }

    @PostMapping("/{id}/claim")
    public ResponseEntity<EmergencyResponseDto> claim(@PathVariable UUID id, @RequestParam String responder) {
        var emergency = emergencyService.claim(id, responder);
        return ResponseEntity.ok(EmergencyRequestMapper.toDto(emergency));
    }

    @GetMapping
    public ResponseEntity<List<EmergencyResponseDto>> list(@RequestParam(required = false) String status) {
        if (status == null) status = "NEW";
        var emergencies = emergencyService.listByStatus(status)
                .stream().map(EmergencyRequestMapper::toDto).toList();
        return ResponseEntity.ok(emergencies);
    }
}
