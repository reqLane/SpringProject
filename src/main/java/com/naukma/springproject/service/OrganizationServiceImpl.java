package com.naukma.springproject.service;

import com.naukma.springproject.entity.OrganizationEntity;
import com.naukma.springproject.entity.StudentEntity;
import com.naukma.springproject.entity.StudentOrganization;
import com.naukma.springproject.entity.key.StudentOrganizationKey;
import com.naukma.springproject.exception.StudentAlreadyEnrolledException;
import com.naukma.springproject.repository.OrganizationRepository;
import com.naukma.springproject.repository.StudentOrganizationRepository;
import com.naukma.springproject.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final StudentRepository studentRepository;
    private final StudentOrganizationRepository studentOrganizationRepository;

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
    }

    @Override
    public OrganizationEntity get(Long organizationId) {
        if(organizationRepository.findById(organizationId).isEmpty())
            throw new NoSuchElementException("Organization not found.");

        return organizationRepository.findById(organizationId).get();
    }

    @Override
    public void addStudent(Long organizationId, Long studentId) throws StudentAlreadyEnrolledException {
        if(organizationRepository.findById(organizationId).isEmpty())
            throw new NoSuchElementException("Organization not found.");
        OrganizationEntity organization = organizationRepository.findById(organizationId).get();
        if(studentRepository.findById(studentId).isEmpty())
            throw new NoSuchElementException("Student not found.");
        StudentEntity student = studentRepository.findById(studentId).get();

        //already enrolled
        StudentOrganizationKey embeddedId = new StudentOrganizationKey();
        embeddedId.setOrganizationId(organizationId);
        embeddedId.setStudentId(studentId);
        if(studentOrganizationRepository.findById(embeddedId).isPresent())
            throw new StudentAlreadyEnrolledException("Student already enrolled in the organization");

        StudentOrganization connection = new StudentOrganization();
        connection.setOrganization(organization);
        connection.setStudent(student);
        studentOrganizationRepository.save(connection);
    }

    @Override
    public void delete(Long organizationId) {
        organizationRepository.deleteById(organizationId);
    }
}
