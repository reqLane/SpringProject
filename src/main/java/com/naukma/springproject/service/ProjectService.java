package com.naukma.springproject.service;

import com.naukma.springproject.exception.StudentAlreadyEnrolledException;
import com.naukma.springproject.exception.StudentIsNotEnrolledException;
import com.naukma.springproject.model.Project;

public interface ProjectService {

    void addTo(Project project, String organizationName);

    void addStudent(String projectName, String studentLogin) throws StudentAlreadyEnrolledException, StudentIsNotEnrolledException;

    Project get(Long projectId);

    void setHoursForMember(String projectName, String studentLogin, Long hoursAmount) throws StudentIsNotEnrolledException;

    void delete(Long projectId);
}
