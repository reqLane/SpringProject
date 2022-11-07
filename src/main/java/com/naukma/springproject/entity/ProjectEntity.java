package com.naukma.springproject.entity;

import com.naukma.springproject.model.Project;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    OrganizationEntity organization;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "project",
            cascade = CascadeType.ALL)
    Set<StudentProject> studentProjects;

    public static ProjectEntity toEntity(Project model) {
        ProjectEntity entity = new ProjectEntity();
        entity.setName(model.getName());
        return entity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public Set<StudentProject> getStudentProjects() {
        return studentProjects;
    }

    public void setStudentProjects(Set<StudentProject> studentProjects) {
        this.studentProjects = studentProjects;
    }
}
