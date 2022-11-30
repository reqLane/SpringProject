package com.naukma.springproject.service;

import com.naukma.springproject.entity.*;
import com.naukma.springproject.entity.key.StudentOrganizationKey;
import com.naukma.springproject.exception.StudentAlreadyEnrolledException;
import com.naukma.springproject.model.Organization;
import com.naukma.springproject.model.Pair;
import com.naukma.springproject.repository.OrganizationRepository;
import com.naukma.springproject.repository.StudentOrganizationRepository;
import com.naukma.springproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final UserRepository studentRepository;
    private final StudentOrganizationRepository studentOrganizationRepository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository,
                                   UserRepository studentRepository,
                                   StudentOrganizationRepository studentOrganizationRepository) {
        this.organizationRepository = organizationRepository;
        this.studentRepository = studentRepository;
        this.studentOrganizationRepository = studentOrganizationRepository;
    }

    @Override
    public void register(Organization organization) {
        organizationRepository.save(OrganizationEntity.toEntity(organization));
    }

    @Override
    public Organization get(Long organizationId) {
        if(organizationRepository.findById(organizationId).isEmpty())
            throw new NoSuchElementException("Organization not found.");

        return Organization.toModel(organizationRepository.findById(organizationId).get());
    }

    @Override
    public void addStudent(Long organizationId, String studentLogin) throws StudentAlreadyEnrolledException {
        if(organizationRepository.findById(organizationId).isEmpty())
            throw new NoSuchElementException("Organization not found.");
        OrganizationEntity organization = organizationRepository.findById(organizationId).get();
        if(studentRepository.findByLogin(studentLogin) == null)
            throw new NoSuchElementException("Student not found.");
        UserEntity student = studentRepository.findByLogin(studentLogin);

        //already enrolled
        StudentOrganizationKey embeddedId = new StudentOrganizationKey();
        embeddedId.setOrganizationId(organizationId);
        embeddedId.setStudentId(student.getId());
        if(studentOrganizationRepository.findById(embeddedId).isPresent())
            throw new StudentAlreadyEnrolledException("Student already enrolled in the organization");

        StudentOrganization connection = new StudentOrganization();
        connection.setOrganization(organization);
        connection.setStudent(student);
        studentOrganizationRepository.save(connection);
    }

    public Set<Pair<ProjectEntity, Long>> getProjectsWithStudent(OrganizationEntity org, UserEntity user) {
        Set<Pair<ProjectEntity, Long>> projects = new HashSet<>();

        for (ProjectEntity project : org.getProjects()) {
            for (StudentProject connection : project.getStudentProjects()) {
                if(connection.getStudent().equals(user)) {
                    projects.add(new Pair<>(project, connection.getHours()));
                    break;
                }
            }
        }

        return projects;
    }

    @Override
    public void delete(Long organizationId) {
        organizationRepository.deleteById(organizationId);
    }
}
