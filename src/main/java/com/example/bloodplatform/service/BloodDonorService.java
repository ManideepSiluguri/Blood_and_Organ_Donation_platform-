package com.example.bloodplatform.service;

import com.example.bloodplatform.model.BloodDonor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

public interface BloodDonorService {
    BloodDonor createDonor(BloodDonor donor);
    BloodDonor updateDonor(UUID id, BloodDonor donor);
    void deleteDonor(UUID id);
    Page<BloodDonor> searchDonors(String city, Pageable pageable);
    List<BloodDonor> findByBloodGroup(String bloodGroup);
    BloodDonor getById(UUID id);
    List<BloodDonor> findAll();
}
