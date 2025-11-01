package com.example.bloodplatform.repository;

import com.example.bloodplatform.model.OrganDonor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface OrganDonorRepository extends JpaRepository<OrganDonor, UUID> {
    List<OrganDonor> findByOrganType(String organType);
    List<OrganDonor> findByCity(String city);
    List<OrganDonor> findByStatus(String status);
}
