package com.example.bloodplatform.service.impl;

import com.example.bloodplatform.exception.ResourceNotFoundException;
import com.example.bloodplatform.model.Donor;
import com.example.bloodplatform.repository.DonorRepository;
import com.example.bloodplatform.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class DonorServiceImpl implements DonorService {
    private final DonorRepository donorRepository;

    @Autowired
    public DonorServiceImpl(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @Override
    public Donor createDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    @Override
    public Donor updateDonor(UUID id, Donor donor) {
        Donor ex = donorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Donor", id));
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
    public Page<Donor> searchDonors(String city, Pageable pageable) {
        if (city == null || city.isBlank()) return donorRepository.findAll(pageable);
        return donorRepository.findByAddressContainingIgnoreCase(city, pageable);
    }

    @Override
    public List<Donor> findByBloodGroup(String bloodGroup) {
        return donorRepository.findByBloodGroup(bloodGroup);
    }

    @Override
    public Donor getById(UUID id) {
        return donorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Donor", id));
    }
}
