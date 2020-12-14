package com.kukilej.springdataredisdemo.controller.errorhandler;

import com.kukilej.springdataredisdemo.controller.dto.ResponseMessageDTO;
import com.kukilej.springdataredisdemo.exception.ApplicationNotFoundException;
import com.kukilej.springdataredisdemo.exception.CollegeNotFoundException;
import com.kukilej.springdataredisdemo.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(CollegeNotFoundException.class)
    public ResponseEntity<ResponseMessageDTO> handleCollegeNotFoundException(CollegeNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO(e.getMessage()));
    }
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ResponseMessageDTO> handleStudentNotFoundException(StudentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO(e.getMessage()));
    }
    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<ResponseMessageDTO> handleApplicationNotFoundException(ApplicationNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessageDTO(e.getMessage()));
    }
}