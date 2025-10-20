package com.example.bloodplatform.service.impl;
import com.example.bloodplatform.model.BloodType;
import com.example.bloodplatform.model.Donor;
import com.example.bloodplatform.repository.DonorRepository;
import com.example.bloodplatform.service.DonorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class DonorServiceImpl implements DonorService {
    private final DonorRepository donorRepository;
    @Override
    public Donor register(Donor donor) {
        updateEligibility(donor);
        donor.setAvailable(true);
        return donorRepository.save(donor);
    }
    @Override
    public Optional<Donor> findById(Long id) {
        return donorRepository.findById(id);
    }
    @Override
    public Donor update(Long id, Donor update) {
        return donorRepository.findById(id).map(d -> {
            d.setFullName(update.getFullName());
            d.setPhone(update.getPhone());
            d.setAge(update.getAge());
            d.setNotes(update.getNotes());
            d.setLocation(update.getLocation());
            d.setOrganDonor(update.isOrganDonor());
            d.setAvailable(update.isAvailable());
            d.setLastDonationDate(update.getLastDonationDate());
            updateEligibility(d);
            return donorRepository.save(d);
        }).orElseThrow(() -> new IllegalArgumentException("Donor not found"));
    }
    @Override
    public List<Donor> searchByBloodAndCity(BloodType bloodType, String city) {
        if (city == null || city.isBlank()) {
            return donorRepository.findAvailableByBloodType(bloodType);
        }
        return donorRepository.findAvailableByBloodTypeAndCity(bloodType, city);
    }
    @Override
    public void updateEligibility(Donor donor) {
        if (donor.getLastDonationDate() == null) {
            return;
        }
        LocalDate last = donor.getLastDonationDate();
        int days = Period.between(last, LocalDate.now()).getDays();
        donor.setAvailable(days >= 56);
    }
}
