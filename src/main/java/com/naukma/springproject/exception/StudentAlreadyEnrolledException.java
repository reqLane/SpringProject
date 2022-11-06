package com.naukma.springproject.exception;

public class StudentAlreadyEnrolledException extends Exception {
    public StudentAlreadyEnrolledException(String message) {
        super(message);
    }
}
