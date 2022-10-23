package com.naukma.springproject.service;

import com.naukma.springproject.entity.AdminEntity;

public interface AdminService {

    void register(AdminEntity adminEntity);

    default AdminEntity get(Long id) {
        return null;
    }

    void delete(Long adminId);
}
