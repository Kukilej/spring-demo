package com.kukilej.springdataredisdemo.exception;

public class CollegeNotFoundException extends RuntimeException {

    public CollegeNotFoundException(String collegeId) {
        super("Cannot find college with id:" + collegeId);
    }
}