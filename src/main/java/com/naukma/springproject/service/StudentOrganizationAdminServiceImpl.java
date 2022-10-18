package com.naukma.springproject.service;

import com.naukma.springproject.entity.StudentOrganizationAdminEntity;
import com.naukma.springproject.repository.StudentOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentOrganizationAdminServiceImpl implements StudentOrganizationAdminService{

    private StudentOrganizationRepository studentOrganizationRepository;

    @Autowired
    public StudentOrganizationAdminServiceImpl(StudentOrganizationRepository studentOrganizationRepository) {
        this.studentOrganizationRepository = studentOrganizationRepository;
    }

    @Override
    public void addTo(StudentOrganizationAdminEntity organizationAdminEntity, Long organizationId) {

    }
    @Override
    public void deleteFrom(Long organizationAdminId, Long organizationId) {

    }
}
