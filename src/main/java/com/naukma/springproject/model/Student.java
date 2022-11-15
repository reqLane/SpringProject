package com.naukma.springproject.model;

import com.naukma.springproject.entity.StudentEntity;

import javax.validation.constraints.NotBlank;

public class Student {
    @NotBlank(message = "login is mandatory")
    private String login;
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "surname is mandatory")
    private String surname;

    public static Student toModel(StudentEntity studentEntity) {
        Student model = new Student();
        model.setLogin(studentEntity.getLogin());
        model.setPassword(studentEntity.getPassword());
        model.setName(studentEntity.getName());
        model.setSurname(studentEntity.getSurname());
        return model;
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
}
