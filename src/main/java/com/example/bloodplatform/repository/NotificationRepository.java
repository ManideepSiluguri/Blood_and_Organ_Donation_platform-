package com.example.bloodplatform.repository;

import com.example.bloodplatform.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> { }
