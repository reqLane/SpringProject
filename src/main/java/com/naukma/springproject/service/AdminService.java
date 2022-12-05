package com.naukma.springproject.service;


import com.naukma.springproject.entity.UserEntity;
import com.naukma.springproject.model.User;

public interface AdminService {

    void register(User admin);

    UserEntity get(Long adminId);

    void delete(Long adminId);

}
