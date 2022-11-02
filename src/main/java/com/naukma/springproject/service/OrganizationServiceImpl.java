package com.naukma.springproject.service;

import com.naukma.springproject.entity.OrganizationEntity;
import com.naukma.springproject.entity.StudentEntity;
import com.naukma.springproject.entity.StudentOrganization;
import com.naukma.springproject.repository.OrganizationRepository;
import com.naukma.springproject.repository.StudentOrganizationRepository;
import com.naukma.springproject.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;
    private StudentRepository studentRepository;
    private StudentOrganizationRepository studentOrganizationRepository;

    private final Logger logger = LogManager.getLogger();

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository,
                                   StudentRepository studentRepository,
                                   StudentOrganizationRepository studentOrganizationRepository) {
        this.organizationRepository = organizationRepository;
        this.studentRepository = studentRepository;
        this.studentOrganizationRepository = studentOrganizationRepository;
    }

    @Override
    public void register(OrganizationEntity organizationEntity) {
        organizationRepository.save(organizationEntity);
        logger.info("Organization registered");
    }

    @Override
    public void addStudent(Long organizationId, Long studentId) {
        OrganizationEntity organization = organizationRepository.findById(organizationId).get();
        StudentEntity student = studentRepository.findById(studentId).get();
        StudentOrganization connection = new StudentOrganization();
        connection.setOrganization(organization);
        connection.setStudent(student);
        studentOrganizationRepository.save(connection);
        logger.info("Student added to organization");
    }

    @Override
    public void delete(Long organizationId) {
        organizationRepository.deleteById(organizationId);
        logger.info("Organization deleted");
    }
}
