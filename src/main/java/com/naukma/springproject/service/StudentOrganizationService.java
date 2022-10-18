package com.naukma.springproject.service;

import com.naukma.springproject.entity.StudentOrganizationEntity;
import org.springframework.stereotype.Service;

public interface StudentOrganizationService {

    void register(StudentOrganizationEntity studentOrganizationEntity);

    void delete(Long organizationId);
}
