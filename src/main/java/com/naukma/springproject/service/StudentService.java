package com.naukma.springproject.service;

import com.naukma.springproject.model.User;

public interface StudentService {

    void register(User student);

    User get(Long studentId);

    User getUserByLogin(String login);

    void delete(Long studentId);


}
