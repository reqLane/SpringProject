package com.naukma.springproject.service;

import com.naukma.springproject.entity.StudentProjectEntity;
import com.naukma.springproject.repository.StudentProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentProjectServiceImpl implements StudentProjectService{

    private StudentProjectRepository studentProjectRepository;

    @Autowired
    public StudentProjectServiceImpl(StudentProjectRepository studentProjectRepository) {
        this.studentProjectRepository = studentProjectRepository;
    }

    @Override
    public void addTo(StudentProjectEntity studentProjectEntity, Long organizationId) {

    }

    @Override
    public void delete(Long studentProjectId) {
    }
}
