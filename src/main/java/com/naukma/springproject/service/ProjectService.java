package com.naukma.springproject.service;

import com.naukma.springproject.exception.StudentAlreadyEnrolledException;
import com.naukma.springproject.exception.StudentIsNotEnrolledException;
import com.naukma.springproject.model.Project;

public interface ProjectService {

    void addTo(Project project, Long organizationId);

    void addStudent(Long projectId, Long studentId) throws StudentAlreadyEnrolledException, StudentIsNotEnrolledException;

    Project get(Long projectId);

    void setHoursForMember(Long projectId, Long studentId, Long hoursAmount) throws StudentIsNotEnrolledException;

    void delete(Long projectId);
}
