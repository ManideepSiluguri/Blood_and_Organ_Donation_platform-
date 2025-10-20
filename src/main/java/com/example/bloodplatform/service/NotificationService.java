package com.example.bloodplatform.service;

import com.example.bloodplatform.model.EmergencyRequest;

public interface NotificationService {
    void notifyMatchingDonorsForEmergency(EmergencyRequest request);
    void sendEmail(String to, String subject, String body);
    void sendSms(String to, String body);
}
