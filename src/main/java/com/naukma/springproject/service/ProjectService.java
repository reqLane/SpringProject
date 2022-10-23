package com.naukma.springproject.service;

import com.naukma.springproject.entity.ProjectEntity;

public interface ProjectService {

    void addTo(ProjectEntity projectEntity, Long organizationId);

    void addStudent(Long projectId, Long studentId);

    void delete(Long projectId);
}
