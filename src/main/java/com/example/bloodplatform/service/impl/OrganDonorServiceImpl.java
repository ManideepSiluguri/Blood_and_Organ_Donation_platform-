package com.example.bloodplatform.service.impl;

import com.example.bloodplatform.exception.ResourceNotFoundException;
import com.example.bloodplatform.model.OrganDonor;
import com.example.bloodplatform.repository.OrganDonorRepository;
import com.example.bloodplatform.service.OrganDonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class OrganDonorServiceImpl implements OrganDonorService {
    private final OrganDonorRepository organRepository;

    @Autowired
    public OrganDonorServiceImpl(OrganDonorRepository organRepository) {
        this.organRepository = organRepository;
    }

    @Override
    public OrganDonor createOrganDonor(OrganDonor organDonor) {
        return organRepository.save(organDonor);
    }

    @Override
    public OrganDonor getById(UUID id) {
        return organRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("OrganDonor", id));
    }

    @Override
    public List<OrganDonor> findAll() {
        return organRepository.findAll();
    }

    @Override
    public List<OrganDonor> findByOrganType(String organType) {
        return organRepository.findByOrganType(organType);
    }

    @Override
    public List<OrganDonor> findByCity(String city) {
        return organRepository.findByCity(city);
    }

    @Override
    public List<OrganDonor> findByStatus(String status) {
        return organRepository.findByStatus(status);
    }
}
