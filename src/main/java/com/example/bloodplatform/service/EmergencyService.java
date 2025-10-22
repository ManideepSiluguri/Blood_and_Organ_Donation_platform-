package com.example.bloodplatform.service;

import com.example.bloodplatform.model.EmergencyRequest;

import java.util.List;
import java.util.UUID;

public interface EmergencyService {
    EmergencyRequest create(EmergencyRequest request);
    EmergencyRequest claim(UUID id, String responder);
    List<EmergencyRequest> listByStatus(String status);
}
