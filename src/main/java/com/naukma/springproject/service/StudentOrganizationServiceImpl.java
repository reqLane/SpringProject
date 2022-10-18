package com.naukma.springproject.service;

import com.naukma.springproject.entity.StudentOrganizationEntity;
import com.naukma.springproject.repository.StudentOrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentOrganizationServiceImpl implements StudentOrganizationService {

    private StudentOrganizationRepository studentOrganizationRepository;

    @Autowired
    public StudentOrganizationServiceImpl(StudentOrganizationRepository studentOrganizationRepository) {
        this.studentOrganizationRepository = studentOrganizationRepository;
    }

    @Override
    public void register(StudentOrganizationEntity studentOrganizationEntity) {
    }

    @Override
    public void delete(Long organizationId) {
    }
}
