package com.naukma.springproject.service;

import com.naukma.springproject.entity.StudentEntity;
import org.springframework.stereotype.Service;

public interface StudentService {
    void register(StudentEntity studentEntity);

    void delete(Long studentId);
}
