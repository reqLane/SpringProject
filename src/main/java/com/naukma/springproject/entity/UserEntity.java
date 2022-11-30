package com.naukma.springproject.entity;

import com.naukma.springproject.enums.Role;
import com.naukma.springproject.model.User;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserEntity {

    public UserEntity(String login, String password, String name, String surname, Role role, String email){
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.email = email;
    }
    public UserEntity() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String name;
    private String surname;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;


    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "student",
            cascade = CascadeType.ALL)
    Set<StudentOrganization> studentOrganizations;
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "student",
            cascade = CascadeType.ALL)
    Set<StudentProject> studentProjects;

    public static UserEntity toEntity(User model) {
        UserEntity entity = new UserEntity();
        entity.setLogin(model.getLogin());
        entity.setPassword(model.getPassword());
        entity.setName(model.getName());
        entity.setSurname(model.getSurname());
        entity.setEmail(model.getEmail());
        return entity;
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

    public Role getRole(){
        return this.role;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
