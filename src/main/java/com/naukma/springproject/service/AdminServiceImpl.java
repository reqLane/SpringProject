package com.naukma.springproject.service;

import com.naukma.springproject.entity.UserEntity;
import com.naukma.springproject.enums.Role;
import com.naukma.springproject.model.User;
import com.naukma.springproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AdminServiceImpl implements AdminService{

    private final UserRepository adminRepository;

    @Autowired
    public AdminServiceImpl(UserRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void register(User admin){
        UserEntity ue = UserEntity.toEntity(admin);
        ue.setRole(Role.ADMIN);
        adminRepository.save(ue);
    }

    public UserEntity get(Long adminId) {
        if(adminRepository.findById(adminId).isEmpty())
            throw new NoSuchElementException("Admin not found.");

        return adminRepository.findById(adminId).get();
    }

    public void delete(Long adminId){
        adminRepository.deleteById(adminId);
    }
}
