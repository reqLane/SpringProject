package com.naukma.springproject.service;

import com.naukma.springproject.entity.StudentEntity;
import com.naukma.springproject.model.Student;
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

    public void register(Student student){
        studentRepository.save(StudentEntity.toEntity(student));
    }

    @Override
    public Student get(Long studentId) {
        if(studentRepository.findById(studentId).isEmpty())
            throw new NoSuchElementException("Student not found.");

        return Student.toModel(studentRepository.findById(studentId).get());
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
