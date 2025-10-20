package com.example.bloodplatform.repository;

import com.example.bloodplatform.model.BloodType;
import com.example.bloodplatform.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonorRepository extends JpaRepository<Donor,Long> {

    @Query("SELECT d FROM Donor d WHERE d.bloodType = :bloodType AND d.available = true")
    List<Donor> findAvailableByBloodType(@Param("bloodType") BloodType bloodType);


    @Query("SELECT d FROM Donor d WHERE d.bloodType = :bloodType AND d.available = true AND LOWER(d.location.city) = LOWER(:city)")
    List<Donor> findAvailableByBloodTypeAndCity(@Param("bloodType") BloodType bloodType,
                                                @Param("city") String city);


    @Query("SELECT d FROM Donor d WHERE d.bloodType = :bloodType AND d.available = true AND LOWER(d.location.state) = LOWER(:state)")
    List<Donor> findAvailableByBloodTypeAndState(@Param("bloodType") BloodType bloodType,
                                                 @Param("state") String state);


    @Query("SELECT d FROM Donor d WHERE d.bloodType = :bloodType AND d.available = true " +
            "AND (:city IS NULL OR LOWER(d.location.city) = LOWER(:city))")
    List<Donor> searchAvailable(@Param("bloodType") BloodType bloodType,
                                @Param("city") String city);




    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Donor d WHERE d.email = :email")
    boolean existsByEmail(@Param("email") String email);


    @Query("SELECT d FROM Donor d WHERE d.email = :email")
    Donor findByEmail(@Param("email") String email);
}
