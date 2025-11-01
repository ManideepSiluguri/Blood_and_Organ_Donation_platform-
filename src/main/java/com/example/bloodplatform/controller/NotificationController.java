package com.example.bloodplatform.controller;

import com.example.bloodplatform.dto.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Send notification to specific donor
    public void notifyDonor(String donorId, String title, String message, String type) {
        NotificationMessage notification = new NotificationMessage();
        notification.setId(UUID.randomUUID().toString());
        notification.setType(type);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setDonorId(donorId);
        notification.setTimestamp(LocalDateTime.now());
        notification.setStatus("UNREAD");

        // Send to specific donor's queue
        messagingTemplate.convertAndSend("/topic/donor/" + donorId, notification);
    }

    // Broadcast to all donors of specific blood group
    public void broadcastToBloodGroup(String bloodGroup, String title, String message) {
        NotificationMessage notification = new NotificationMessage();
        notification.setId(UUID.randomUUID().toString());
        notification.setType("BLOOD_REQUEST");
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notification.setStatus("UNREAD");

        messagingTemplate.convertAndSend("/topic/blood-group/" + bloodGroup, notification);
    }

    // Broadcast to all organ donors
    public void broadcastToOrganType(String organType, String title, String message) {
        NotificationMessage notification = new NotificationMessage();
        notification.setId(UUID.randomUUID().toString());
        notification.setType("ORGAN_REQUEST");
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notification.setStatus("UNREAD");

        messagingTemplate.convertAndSend("/topic/organ-type/" + organType, notification);
    }

    // Emergency broadcast to all donors in area
    public void emergencyBroadcast(String city, String title, String message) {
        NotificationMessage notification = new NotificationMessage();
        notification.setId(UUID.randomUUID().toString());
        notification.setType("EMERGENCY");
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());
        notification.setStatus("UNREAD");

        messagingTemplate.convertAndSend("/topic/emergency/" + city, notification);
    }
}
