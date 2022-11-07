package com.naukma.springproject.entity;


import com.naukma.springproject.model.Student;

import javax.persistence.*;
import java.util.Set;

@Entity
public class StudentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private String name;
    private String surname;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "student",
            cascade = CascadeType.ALL)
    Set<StudentOrganization> studentOrganizations;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "student",
            cascade = CascadeType.ALL)
    Set<StudentProject> studentProjects;

    public static StudentEntity toEntity(Student model) {
        StudentEntity entity = new StudentEntity();
        entity.setLogin(model.getLogin());
        entity.setPassword(model.getPassword());
        entity.setName(model.getName());
        entity.setSurname(model.getSurname());
        return entity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<StudentOrganization> getStudentOrganizations() {
        return studentOrganizations;
    }

    public void setStudentOrganizations(Set<StudentOrganization> studentOrganizations) {
        this.studentOrganizations = studentOrganizations;
    }

    public Set<StudentProject> getStudentProjects() {
        return studentProjects;
    }

    public void setStudentProjects(Set<StudentProject> studentProjects) {
        this.studentProjects = studentProjects;
    }
}
