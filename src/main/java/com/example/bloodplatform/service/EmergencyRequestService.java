package com.example.bloodplatform.service;

import com.example.bloodplatform.model.EmergencyRequest;

import java.util.List;

public interface EmergencyRequestService {
    EmergencyRequest create(EmergencyRequest r);
    List<EmergencyRequest> openRequests();
    void markResolved(Long id);
}
