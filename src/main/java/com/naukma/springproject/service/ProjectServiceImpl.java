package com.naukma.springproject.service;

import com.naukma.springproject.entity.*;
import com.naukma.springproject.entity.key.StudentOrganizationKey;
import com.naukma.springproject.entity.key.StudentProjectKey;
import com.naukma.springproject.exception.StudentAlreadyEnrolledException;
import com.naukma.springproject.exception.StudentIsNotEnrolledException;
import com.naukma.springproject.model.Project;
import com.naukma.springproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final OrganizationRepository organizationRepository;
    private final UserRepository studentRepository;
    private final StudentProjectRepository studentProjectRepository;
    private final StudentOrganizationRepository studentOrganizationRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository,
                              OrganizationRepository organizationRepository,
                              UserRepository studentRepository,
                              StudentProjectRepository studentProjectRepository,
                              StudentOrganizationRepository studentOrganizationRepository) {
        this.projectRepository = projectRepository;
        this.organizationRepository = organizationRepository;
        this.studentRepository = studentRepository;
        this.studentProjectRepository = studentProjectRepository;
        this.studentOrganizationRepository = studentOrganizationRepository;
    }

    @Override
    public void addTo(Project project, String organizationName) {
        if(organizationRepository.findByName(organizationName) == null)
            throw new NoSuchElementException("Organization not found");
        if(projectRepository.findByName(project.getName()) != null)
            throw new IllegalArgumentException("Project name already used");
        OrganizationEntity organization = organizationRepository.findByName(organizationName);

        ProjectEntity projectEntity = ProjectEntity.toEntity(project);

        projectEntity.setOrganization(organization);
        projectRepository.save(projectEntity);
    }

    @Override
    public void addStudent(String projectName, String studentLogin) throws StudentAlreadyEnrolledException, StudentIsNotEnrolledException {
        if(projectRepository.findByName(projectName) == null)
            throw new NoSuchElementException("Project not found");
        ProjectEntity project = projectRepository.findByName(projectName);
        if(studentRepository.findByLogin(studentLogin) == null)
            throw new NoSuchElementException("Student not found.");
        UserEntity student = studentRepository.findByLogin(studentLogin);

        //check if student belongs to project's organization
        ProjectEntity projectEntity = projectRepository.findById(project.getId()).get();
        if(!studentBelongsToOrganization(student.getId(), projectEntity.getOrganization().getId()))
            throw new StudentIsNotEnrolledException("Student is not enrolled in the project's organization");

        //already enrolled
        if(studentBelongsToProject(project.getId(), student.getId()))
            throw new StudentAlreadyEnrolledException("Student already enrolled in the project");

        StudentProject connection = new StudentProject();
        connection.setProject(project);
        connection.setStudent(student);
        studentProjectRepository.save(connection);
    }

    @Override
    public Project get(Long projectId) {
        if(projectRepository.findById(projectId).isEmpty())
            throw new NoSuchElementException("Project not found.");

        return Project.toModel(projectRepository.findById(projectId).get());
    }

    @Override
    public void setHoursForMember(String studentLogin, String projectName, Long hoursAmount) throws StudentIsNotEnrolledException {
        if(projectRepository.findByName(projectName) == null)
            throw new NoSuchElementException("Project not found");
        if(studentRepository.findByLogin(studentLogin) == null)
            throw new NoSuchElementException("Student not found");

        ProjectEntity project = projectRepository.findByName(projectName);
        UserEntity student = studentRepository.findByLogin(studentLogin);

        //check if student belongs to the project
        if(!studentBelongsToProject(project.getId(), student.getId()))
            throw new StudentIsNotEnrolledException("Student is not enrolled in the project");

        StudentProjectKey embeddedId = new StudentProjectKey();
        embeddedId.setProjectId(project.getId());
        embeddedId.setStudentId(student.getId());
        StudentProject connection = studentProjectRepository.findById(embeddedId).get();
        connection.setHours(hoursAmount);
        studentProjectRepository.save(connection);
    }

    @Override
    public void delete(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    private boolean studentBelongsToProject(Long projectId, Long studentId) {
        StudentProjectKey embeddedId = new StudentProjectKey();
        embeddedId.setProjectId(projectId);
        embeddedId.setStudentId(studentId);
        return studentProjectRepository.findById(embeddedId).isPresent();
    }
    private boolean studentBelongsToOrganization(Long studentId, Long organizationId) {
        StudentOrganizationKey embeddedId = new StudentOrganizationKey();
        embeddedId.setStudentId(studentId);
        embeddedId.setOrganizationId(organizationId);
        return studentOrganizationRepository.findById(embeddedId).isPresent();
    }
}
