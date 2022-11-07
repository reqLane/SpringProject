package com.naukma.springproject.service;

import com.naukma.springproject.exception.StudentAlreadyEnrolledException;
import com.naukma.springproject.model.Organization;

public interface OrganizationService {

    void register(Organization organization);

    Organization get(Long organizationId);

    void addStudent(Long organizationId, Long studentId) throws StudentAlreadyEnrolledException;

    void delete(Long organizationId);
}
