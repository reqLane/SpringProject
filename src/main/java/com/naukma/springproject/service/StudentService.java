package com.naukma.springproject.service;

import com.naukma.springproject.entity.StudentEntity;

public interface StudentService {

    void register(StudentEntity studentEntity);

    void delete(Long studentId);
}
