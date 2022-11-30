package com.naukma.springproject.service;

import com.naukma.springproject.entity.OrganizationEntity;
import com.naukma.springproject.entity.UserEntity;
import com.naukma.springproject.model.User;

import java.util.Set;

public interface StudentService {

    void register(User student);

    User get(Long studentId);

    UserEntity getUserByLogin(String login);

    Set<OrganizationEntity> getOrganizations(UserEntity user);

    void delete(Long studentId);
}
