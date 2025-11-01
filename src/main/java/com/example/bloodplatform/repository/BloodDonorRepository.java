package com.example.bloodplatform.repository;

import com.example.bloodplatform.model.BloodDonor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface BloodDonorRepository extends JpaRepository<BloodDonor, UUID> {
    List<BloodDonor> findByBloodGroup(String bloodGroup);
    Page<BloodDonor> findByAddressContainingIgnoreCase(String city, Pageable pageable);
}
