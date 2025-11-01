package com.example.bloodplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage {
    private String id;
    private String type; // BLOOD_REQUEST, ORGAN_REQUEST, MATCH_FOUND, EMERGENCY
    private String title;
    private String message;
    private String donorId;
    private String requestId;
    private LocalDateTime timestamp;
    private String status; // READ, UNREAD
}
