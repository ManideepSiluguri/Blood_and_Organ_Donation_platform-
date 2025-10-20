package com.example.bloodplatform.service;

import com.example.bloodplatform.model.EmergencyRequest;
import com.example.bloodplatform.model.Donor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmtpNotificationService implements NotificationService {

    private final JavaMailSender mailSender;
    private final DonorService donorService;

    @Override
    public void notifyMatchingDonorsForEmergency(EmergencyRequest request) {
        // simple: find donors by blood + city
        if (request.getBloodType() == null) return;
        donorService.searchByBloodAndCity(request.getBloodType(), request.getCity())
                .forEach(d -> {
                    String subject = "Emergency request: " + request.getBloodType();
                    String body = String.format("Hospital: %s\nRequired: %d\nContact: %s\nNotes: %s",
                            request.getHospitalName(),
                            request.getUnitsRequired() == null ? 0 : request.getUnitsRequired(),
                            request.getContactPhone(),
                            request.getNotes() == null ? "" : request.getNotes());

                    if (d.getEmail() != null && !d.getEmail().isBlank()) {
                        sendEmail(d.getEmail(), subject, body);
                    }
                    if (d.getPhone() != null && !d.getPhone().isBlank()) {
                        sendSms(d.getPhone(), subject + " - " + request.getContactPhone());
                    }
                });
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(body);
            mailSender.send(msg);
        } catch (Exception ex) {
            // log in production
            System.err.println("Failed to send email to " + to + ": " + ex.getMessage());
        }
    }

    @Override
    public void sendSms(String to, String body) {
        // no-op default; add Twilio implementation if needed
        // Example: use Twilio SDK in a TwilioNotificationService
    }
}
