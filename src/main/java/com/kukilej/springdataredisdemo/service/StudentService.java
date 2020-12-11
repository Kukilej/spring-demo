package com.kukilej.springdataredisdemo.service;

import com.kukilej.springdataredisdemo.exception.StudentNotFoundException;
import com.kukilej.springdataredisdemo.model.Student;
import com.kukilej.springdataredisdemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(final Student student) {
        return studentRepository.save(student);
    }
    public Student deleteStudent(final Long studentId) throws StudentNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
        studentRepository.delete(student);
        return student;
    }
    public Student updateStudent(final Student student)  {
        studentRepository.findById(student.getId()).orElseThrow(() -> new StudentNotFoundException(student.getId()));

        return studentRepository.save(student);
    }

    public Student findById(final Long studentId) throws StudentNotFoundException {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
    }

    public List<Student> getAllStudents() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return studentRepository.findAll();
    }
}
