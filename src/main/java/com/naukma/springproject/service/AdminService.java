package com.naukma.springproject.service;

import com.naukma.springproject.entity.AdminEntity;
import org.springframework.stereotype.Service;

public interface AdminService {
    void register(AdminEntity adminEntity);

    void delete(Long adminId);
}
