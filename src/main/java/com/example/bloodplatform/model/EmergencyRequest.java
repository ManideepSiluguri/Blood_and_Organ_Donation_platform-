package com.example.bloodplatform.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "emergency_request")
public class EmergencyRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String description;
    private String location;
    private String contactPhone;
    private String bloodGroupNeeded;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String status; // NEW, RESPONDED, CLOSED
    private String responder; // username or phone who claimed

    public EmergencyRequest() {}

    // getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    public String getBloodGroupNeeded() { return bloodGroupNeeded; }
    public void setBloodGroupNeeded(String bloodGroupNeeded) { this.bloodGroupNeeded = bloodGroupNeeded; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getResponder() { return responder; }
    public void setResponder(String responder) { this.responder = responder; }
}
