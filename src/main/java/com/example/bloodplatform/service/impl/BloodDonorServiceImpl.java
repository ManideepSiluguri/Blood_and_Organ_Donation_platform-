package com.example.bloodplatform.service.impl;

import com.example.bloodplatform.exception.ResourceNotFoundException;
import com.example.bloodplatform.model.BloodDonor;
import com.example.bloodplatform.repository.BloodDonorRepository;
import com.example.bloodplatform.service.BloodDonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class BloodDonorServiceImpl implements BloodDonorService {
    private final BloodDonorRepository donorRepository;

    @Autowired
    public BloodDonorServiceImpl(BloodDonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @Override
    public BloodDonor createDonor(BloodDonor donor) {
        return donorRepository.save(donor);
    }

    @Override
    public BloodDonor updateDonor(UUID id, BloodDonor donor) {
        BloodDonor ex = donorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BloodDonor", id));
        ex.setFirstName(donor.getFirstName());
        ex.setLastName(donor.getLastName());
        ex.setPhone(donor.getPhone());
        ex.setAddress(donor.getAddress());
        ex.setBloodGroup(donor.getBloodGroup());
        ex.setLastDonationDate(donor.getLastDonationDate());
        ex.setActive(donor.getActive());
        return donorRepository.save(ex);
    }

    @Override
    public void deleteDonor(UUID id) {
        donorRepository.deleteById(id);
    }

    @Override
    public Page<BloodDonor> searchDonors(String city, Pageable pageable) {
        if (city == null || city.isBlank()) return donorRepository.findAll(pageable);
        return donorRepository.findByAddressContainingIgnoreCase(city, pageable);
    }

    @Override
    public List<BloodDonor> findByBloodGroup(String bloodGroup) {
        return donorRepository.findByBloodGroup(bloodGroup);
    }

    @Override
    public BloodDonor getById(UUID id) {
        return donorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("BloodDonor", id));
    }
}
