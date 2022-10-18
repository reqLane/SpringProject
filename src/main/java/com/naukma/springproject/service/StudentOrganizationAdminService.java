package com.naukma.springproject.service;

import com.naukma.springproject.entity.StudentOrganizationAdminEntity;
import com.naukma.springproject.repository.StudentOrganizationRepository;
import org.springframework.stereotype.Service;

public interface StudentOrganizationAdminService {


    void addTo(StudentOrganizationAdminEntity organizationAdminEntity, Long organizationId);

    void deleteFrom(Long organizationAdminId, Long organizationId);
}
