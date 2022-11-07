package com.naukma.springproject.service;

import com.naukma.springproject.model.Student;

public interface StudentService {

    void register(Student student);

    Student get(Long studentId);

    void delete(Long studentId);
}
