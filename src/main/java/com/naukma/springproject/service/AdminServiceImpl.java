package com.naukma.springproject.service;

import com.naukma.springproject.entity.AdminEntity;
import com.naukma.springproject.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void register(AdminEntity adminEntity){
        adminRepository.save(adminEntity);
    }

    public AdminEntity get(Long id) {
        return adminRepository.findById(id).get();
    }

    public void delete(Long adminId){
        adminRepository.deleteById(adminId);
    }
}
