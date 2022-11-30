package com.naukma.springproject.service;

import com.naukma.springproject.entity.OrganizationEntity;
import com.naukma.springproject.entity.StudentOrganization;
import com.naukma.springproject.entity.UserEntity;
import com.naukma.springproject.enums.Role;
import com.naukma.springproject.model.User;
import com.naukma.springproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@Service
public class StudentServiceImpl implements StudentService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository studentRepository;

    @Autowired
    public StudentServiceImpl(PasswordEncoder passwordEncoder, UserRepository studentRepository) {
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
    }

    public void register(User student){
        UserEntity ue = UserEntity.toEntity(student);
        ue.setPassword(passwordEncoder.encode(ue.getPassword()));
        ue.setRole(Role.STUDENT);
        studentRepository.save(ue);
    }

    @Override
    public User get(Long studentId) {
        if(studentRepository.findById(studentId).isEmpty())
            throw new NoSuchElementException("Student not found.");

        return User.toModel(studentRepository.findById(studentId).get());
    }

    @Override
    public UserEntity getUserByLogin(String login) {
        if(studentRepository.findByLogin(login) == null)
            throw new NoSuchElementException("Student not found.");

        return studentRepository.findByLogin(login);
    }

    public Set<OrganizationEntity> getOrganizations(UserEntity user) {
        Set<OrganizationEntity> orgs = new TreeSet<>();

        for (StudentOrganization connection : user.getStudentOrganizations()) {
            orgs.add(connection.getOrganization());
        }

        return orgs;
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
