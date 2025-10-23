package com.example.bloodplatform.service;

import com.example.bloodplatform.model.Donor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DonorService {
    Donor createDonor(Donor donor);
    Donor updateDonor(UUID id, Donor donor);
    void deleteDonor(UUID id);
    Page<Donor> searchDonors(String city, Pageable pageable);
    List<Donor> findByBloodGroup(String bloodGroup);
    Donor getById(UUID id);
}
