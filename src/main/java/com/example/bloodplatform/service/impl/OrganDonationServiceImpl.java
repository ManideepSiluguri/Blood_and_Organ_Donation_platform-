package com.example.bloodplatform.service.impl;

import com.example.bloodplatform.exception.ResourceNotFoundException;
import com.example.bloodplatform.model.OrganDonation;
import com.example.bloodplatform.repository.OrganDonationRepository;
import com.example.bloodplatform.service.OrganDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class OrganDonationServiceImpl implements OrganDonationService {
    private final OrganDonationRepository organRepository;

    @Autowired
    public OrganDonationServiceImpl(OrganDonationRepository organRepository) {
        this.organRepository = organRepository;
    }

    @Override
    public OrganDonation createOrganDonation(OrganDonation organDonation) {
        return organRepository.save(organDonation);
    }

    @Override
    public OrganDonation getById(UUID id) {
        return organRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrganDonation", id));
    }

    @Override
    public List<OrganDonation> findByOrganType(String organType) {
        return organRepository.findByOrganType(organType);
    }

    @Override
    public List<OrganDonation> findAll() {
        return organRepository.findAll();
    }
}
