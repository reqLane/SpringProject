package com.naukma.springproject.service;

import com.naukma.springproject.entity.AdminEntity;

public interface AdminService {

    void register(AdminEntity adminEntity);

    AdminEntity get(Long adminId);

    void delete(Long adminId);
}
