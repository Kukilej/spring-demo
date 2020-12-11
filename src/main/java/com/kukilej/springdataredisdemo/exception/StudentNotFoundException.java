package com.kukilej.springdataredisdemo.exception;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long studentId) {
        super("Cannot find student with id:" + studentId);
    }
}