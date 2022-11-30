package com.naukma.springproject.service;

import com.naukma.springproject.entity.OrganizationEntity;
import com.naukma.springproject.entity.ProjectEntity;
import com.naukma.springproject.entity.UserEntity;
import com.naukma.springproject.exception.StudentAlreadyEnrolledException;
import com.naukma.springproject.model.Organization;
import com.naukma.springproject.model.Pair;

import java.util.Set;

public interface OrganizationService {

    void register(Organization organization);

    Organization get(Long organizationId);

    void addStudent(Long organizationId, String studentLogin) throws StudentAlreadyEnrolledException;

    Set<Pair<ProjectEntity, Long>> getProjectsWithStudent(OrganizationEntity org, UserEntity user);

    void delete(Long organizationId);
}
