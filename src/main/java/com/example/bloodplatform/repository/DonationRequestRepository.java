package com.example.bloodplatform.repository;

import com.example.bloodplatform.model.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DonationRequestRepository extends JpaRepository<DonationRequest, UUID> {
    List<DonationRequest> findByStatus(String status);
}
