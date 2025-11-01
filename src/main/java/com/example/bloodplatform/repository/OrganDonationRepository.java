package com.example.bloodplatform.repository;

import com.example.bloodplatform.model.OrganDonation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface OrganDonationRepository extends JpaRepository<OrganDonation, UUID> {
    List<OrganDonation> findByOrganType(String organType);
}
