package com.naukma.springproject.service;

import com.naukma.springproject.entity.UserEntity;
import com.naukma.springproject.enums.Role;
import com.naukma.springproject.model.User;
import com.naukma.springproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService{

    private final UserRepository studentRepository;

    @Autowired
    public StudentServiceImpl(UserRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void register(User student){
        UserEntity ue = UserEntity.toEntity(student);
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
    public User getUserByLogin(String login) {
        if(studentRepository.findByLogin(login)==null)
            throw new NoSuchElementException("Student not found.");

        return User.toModel(studentRepository.findByLogin(login));
    }

    @Override
    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }

//    public UserDetails loadUserByLogin(String login) throws UsernameNotFoundException {
//        UserEntity user = studentRepository.findByLogin(login);
//        if (user == null) {
//            throw new UsernameNotFoundException("No user found with this login");
//        }
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//        List<GrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority(user.getRole().name()));
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(), user.getPassword().toLowerCase(), enabled, accountNonExpired,
//                credentialsNonExpired, accountNonLocked, roles);
//    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleException(NoSuchElementException e) {
        //logging error
        return e.getMessage();
    }

}
