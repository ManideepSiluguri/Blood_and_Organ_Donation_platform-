package com.example.bloodplatform.service.impl;

import com.example.bloodplatform.exception.ResourceNotFoundException;
import com.example.bloodplatform.model.EmergencyRequest;
import com.example.bloodplatform.repository.EmergencyRequestRepository;
import com.example.bloodplatform.service.EmergencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmergencyServiceImpl implements EmergencyService {

    private final EmergencyRequestRepository emergencyRepository;

    @Autowired
    public EmergencyServiceImpl(EmergencyRequestRepository emergencyRepository) { this.emergencyRepository = emergencyRepository; }

    @Override
    public EmergencyRequest create(EmergencyRequest request) { return emergencyRepository.save(request); }

    @Override
    public EmergencyRequest claim(UUID id, String responder) {
        EmergencyRequest ex = emergencyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("EmergencyRequest", id));
        ex.setResponder(responder);
        ex.setStatus("RESPONDED");
        return emergencyRepository.save(ex);
    }

    @Override
    public List<EmergencyRequest> listByStatus(String status) { return emergencyRepository.findByStatus(status); }
}

