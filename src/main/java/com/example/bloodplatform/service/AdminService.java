package com.example.bloodplatform.service;

import com.example.bloodplatform.model.Admin;
import java.util.List;
import java.util.UUID;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin getById(UUID id);
    List<Admin> findAll();
}
