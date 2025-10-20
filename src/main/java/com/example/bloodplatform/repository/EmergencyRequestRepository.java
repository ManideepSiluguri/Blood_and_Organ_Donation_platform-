package com.example.bloodplatform.repository;

import com.example.bloodplatform.model.EmergencyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmergencyRequestRepository extends JpaRepository<EmergencyRequest, Long> {


    @Query("SELECT r FROM EmergencyRequest r WHERE r.resolved = false ORDER BY r.createdAt DESC")
    List<EmergencyRequest> findUnresolvedRequests();


    @Query("SELECT r FROM EmergencyRequest r WHERE r.resolved = false AND LOWER(r.city) = LOWER(:city) ORDER BY r.createdAt DESC")
    List<EmergencyRequest> findUnresolvedByCity(@Param("city") String city);
}
