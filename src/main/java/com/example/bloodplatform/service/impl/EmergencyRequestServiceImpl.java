package com.example.bloodplatform.service.impl;

import com.example.bloodplatform.model.EmergencyRequest;
import com.example.bloodplatform.repository.EmergencyRequestRepository;
import com.example.bloodplatform.service.EmergencyRequestService;
import com.example.bloodplatform.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmergencyRequestServiceImpl implements EmergencyRequestService {

    private final EmergencyRequestRepository repo;
    private final NotificationService notificationService;

    @Override
    public EmergencyRequest create(EmergencyRequest r) {
        r.setCreatedAt(LocalDateTime.now());
        r.setResolved(false);
        EmergencyRequest saved = repo.save(r);
        // notify matching donors (pluggable)
        try {
            notificationService.notifyMatchingDonorsForEmergency(saved);
        } catch (Exception ex) {
            // swallow or log in production; keep app resilient if notification fails
            System.err.println("Notification failed: " + ex.getMessage());
        }
        return saved;
    }

    @Override
    public List<EmergencyRequest> openRequests() {
        return repo.findUnresolvedRequests();
    }

    @Override
    public void markResolved(Long id) {
        repo.findById(id).ifPresent(r -> {
            r.setResolved(true);
            repo.save(r);
        });
    }
}
