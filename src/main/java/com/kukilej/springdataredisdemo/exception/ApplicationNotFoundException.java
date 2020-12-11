package com.kukilej.springdataredisdemo.exception;

public class ApplicationNotFoundException extends RuntimeException {

    public ApplicationNotFoundException(Long applyId) {
        super("Cannot find application with id:" + applyId);
    }
}