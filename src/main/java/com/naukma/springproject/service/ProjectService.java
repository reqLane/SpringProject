package com.naukma.springproject.service;

import com.naukma.springproject.entity.ProjectEntity;
import com.naukma.springproject.exception.StudentAlreadyEnrolledException;
import com.naukma.springproject.exception.StudentIsNotEnrolledException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface ProjectService {

    void addTo(ProjectEntity projectEntity, Long organizationId);

    void addStudent(Long projectId, Long studentId) throws StudentAlreadyEnrolledException, StudentIsNotEnrolledException;

    ProjectEntity get(Long projectId);

    void setHoursForMember(Long projectId, Long studentId, Long hoursAmount) throws StudentIsNotEnrolledException;

    void delete(Long projectId);
}
