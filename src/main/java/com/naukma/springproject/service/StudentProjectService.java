package com.naukma.springproject.service;

import com.naukma.springproject.entity.StudentProjectEntity;
import org.springframework.stereotype.Service;

public interface StudentProjectService{
    void addTo(StudentProjectEntity studentProjectEntity, Long organizationId);

    void delete(Long studentProjectId);
}
