package com.example.bloodplatform.repository;

import com.example.bloodplatform.model.Donor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface DonorRepository extends JpaRepository<Donor, UUID> {
    List<Donor> findByBloodGroup(String bloodGroup);
    Page<Donor> findByAddressContainingIgnoreCase(String city, Pageable pageable);
}
