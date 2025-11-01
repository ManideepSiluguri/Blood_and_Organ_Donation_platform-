package com.example.bloodplatform.service;

import com.example.bloodplatform.model.OrganDonation;
import java.util.List;
import java.util.UUID;

public interface OrganDonationService {
    OrganDonation createOrganDonation(OrganDonation organDonation);
    OrganDonation getById(UUID id);
    List<OrganDonation> findByOrganType(String organType);
    List<OrganDonation> findAll();
}
