package com.example.bloodplatform.repository;

import com.example.bloodplatform.model.EmergencyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface EmergencyRequestRepository extends JpaRepository<EmergencyRequest, UUID> {
    List<EmergencyRequest> findByStatus(String status);
}
