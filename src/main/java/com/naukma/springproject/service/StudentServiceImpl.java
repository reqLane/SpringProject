package com.naukma.springproject.service;

import com.naukma.springproject.entity.StudentEntity;
import com.naukma.springproject.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void register(StudentEntity studentEntity){
        studentRepository.save(studentEntity);
    }

    @Override
    public StudentEntity get(Long studentId) {
        if(studentRepository.findById(studentId).isEmpty())
            throw new NoSuchElementException("Student not found.");

        return studentRepository.findById(studentId).get();
    }

    @Override
    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleException(NoSuchElementException e) {
        //logging error
        return e.getMessage();
    }
}
