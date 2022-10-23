package com.naukma.springproject.service;

import com.naukma.springproject.entity.*;
import com.naukma.springproject.repository.OrganizationRepository;
import com.naukma.springproject.repository.ProjectRepository;
import com.naukma.springproject.repository.StudentProjectRepository;
import com.naukma.springproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    private OrganizationRepository organizationRepository;
    private StudentRepository studentRepository;
    private StudentProjectRepository studentProjectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository,
                              OrganizationRepository organizationRepository,
                              StudentRepository studentRepository,
                              StudentProjectRepository studentProjectRepository) {
        this.projectRepository = projectRepository;
        this.organizationRepository = organizationRepository;
        this.studentRepository = studentRepository;
        this.studentProjectRepository = studentProjectRepository;
    }

    @Override
    public void addTo(ProjectEntity projectEntity, Long organizationId) {
        OrganizationEntity organization = organizationRepository.findById(organizationId).get();
        projectEntity.setOrganization(organization);
        projectRepository.save(projectEntity);
    }

    @Override
    public void addStudent(Long projectId, Long studentId) {
        ProjectEntity project = projectRepository.findById(projectId).get();
        StudentEntity student = studentRepository.findById(studentId).get();
        StudentProject connection = new StudentProject();
        connection.setProject(project);
        connection.setStudent(student);
        studentProjectRepository.save(connection);
    }

    @Override
    public void delete(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}
