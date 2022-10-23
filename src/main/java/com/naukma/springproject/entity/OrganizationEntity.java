package com.naukma.springproject.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class OrganizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "organization",
            cascade = CascadeType.ALL)
    Set<ProjectEntity> projects;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "organization",
            cascade = CascadeType.ALL)
    Set<StudentOrganization> studentOrganizations;


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

    public Set<ProjectEntity> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectEntity> projects) {
        this.projects = projects;
    }

    public Set<StudentOrganization> getStudentOrganizations() {
        return studentOrganizations;
    }

    public void setStudentOrganizations(Set<StudentOrganization> studentOrganizations) {
        this.studentOrganizations = studentOrganizations;
    }
}
