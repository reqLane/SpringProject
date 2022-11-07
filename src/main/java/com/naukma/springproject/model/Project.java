package com.naukma.springproject.model;

import com.naukma.springproject.entity.ProjectEntity;

import javax.validation.constraints.NotBlank;

public class Project {
    @NotBlank(message = "name is mandatory")
    private String name;

    public static Project toModel(ProjectEntity projectEntity) {
        Project model = new Project();
        model.setName(projectEntity.getName());
        return model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
