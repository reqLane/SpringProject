package com.naukma.springproject.model;

import com.naukma.springproject.entity.OrganizationEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

public class Organization {
    @NotBlank(message = "name is mandatory")
    private String name;

    public static Organization toModel(OrganizationEntity organizationEntity) {
        Organization model = new Organization();
        model.setName(organizationEntity.getName());
        return model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
