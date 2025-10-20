package com.example.bloodplatform.service;

import com.example.bloodplatform.model.BloodType;
import com.example.bloodplatform.model.Donor;

import java.util.List;
import java.util.Optional;

public interface DonorService {
    Donor register(Donor donor);
    Optional<Donor> findById(Long id);
    Donor update(Long id, Donor update);
    List<Donor> searchByBloodAndCity(BloodType bloodType, String city);
    void updateEligibility(Donor donor);
}

