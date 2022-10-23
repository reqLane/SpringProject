package com.naukma.springproject.service;

import com.naukma.springproject.entity.OrganizationEntity;

public interface OrganizationService {

    void register(OrganizationEntity organizationEntity);

    void addStudent(Long organizationId, Long studentId);

    void delete(Long organizationId);
}
