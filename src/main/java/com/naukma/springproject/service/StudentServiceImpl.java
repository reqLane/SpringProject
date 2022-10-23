package com.naukma.springproject.service;

import com.naukma.springproject.entity.StudentEntity;
import com.naukma.springproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public void register(StudentEntity studentEntity){
        studentRepository.save(studentEntity);
    }

    @Override
    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }


}
