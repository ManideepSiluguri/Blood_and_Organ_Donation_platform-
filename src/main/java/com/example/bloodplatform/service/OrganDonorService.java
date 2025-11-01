package com.example.bloodplatform.service;

import com.example.bloodplatform.model.OrganDonor;
import java.util.List;
import java.util.UUID;

public interface OrganDonorService {
    OrganDonor createOrganDonor(OrganDonor organDonor);
    OrganDonor getById(UUID id);
    List<OrganDonor> findAll();
    List<OrganDonor> findByOrganType(String organType);
    List<OrganDonor> findByCity(String city);
    List<OrganDonor> findByStatus(String status);
}
