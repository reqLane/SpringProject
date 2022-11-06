package com.naukma.springproject.service;

import com.naukma.springproject.entity.OrganizationEntity;
import com.naukma.springproject.exception.StudentAlreadyEnrolledException;

public interface OrganizationService {

    void register(OrganizationEntity organizationEntity);

    OrganizationEntity get(Long organizationId);

    void addStudent(Long organizationId, Long studentId) throws StudentAlreadyEnrolledException;

    void delete(Long organizationId);
}
