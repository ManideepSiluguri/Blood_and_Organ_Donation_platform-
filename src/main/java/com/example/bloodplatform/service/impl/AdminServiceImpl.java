package com.example.bloodplatform.service.impl;

import com.example.bloodplatform.exception.ResourceNotFoundException;
import com.example.bloodplatform.model.Admin;
import com.example.bloodplatform.repository.AdminRepository;
import com.example.bloodplatform.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin getById(UUID id) {
        return adminRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Admin", id));
    }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }
}
