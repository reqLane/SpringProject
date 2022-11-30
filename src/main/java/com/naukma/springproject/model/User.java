package com.naukma.springproject.model;

import com.naukma.springproject.entity.UserEntity;

import javax.validation.constraints.NotBlank;

public class User {
    @NotBlank(message = "login is mandatory")
    private String login;
    @NotBlank(message = "password is mandatory")
    private String password;
    @NotBlank(message = "name is mandatory")
    private String name;
    @NotBlank(message = "surname is mandatory")
    private String surname;
    @NotBlank(message = "email is mandatory")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static User toModel(UserEntity studentEntity) {
        User model = new User();
        model.setLogin(studentEntity.getLogin());
        model.setPassword(studentEntity.getPassword());
        model.setName(studentEntity.getName());
        model.setSurname(studentEntity.getSurname());
        model.setEmail(studentEntity.getEmail());
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
