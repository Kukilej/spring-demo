package com.kukilej.springdataredisdemo.service;

import com.kukilej.springdataredisdemo.domain.dto.StudentDto;
import com.kukilej.springdataredisdemo.exception.StudentNotFoundException;
import com.kukilej.springdataredisdemo.domain.model.Student;
import com.kukilej.springdataredisdemo.repository.StudentRepository;
import com.kukilej.springdataredisdemo.service.mapper.StudentMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentDto save(final StudentDto studentDto) {
        Student student = studentMapper.fromDto(studentDto);
        student = studentRepository.save(student);
        return studentMapper.toDto(student);
    }

    @CacheEvict(value = "students", key = "#id")
    public void deleteStudent(final Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
    }

    @CachePut(value = "students", key = "#studentDto.getId()")
    public StudentDto updateStudent(final StudentDto studentDto)  {
        studentRepository.findById(studentDto.getId()).orElseThrow(() -> new StudentNotFoundException(studentDto.getId()));

        return save(studentDto);
    }
    @Transactional(readOnly = true)
    @Cacheable(value = "students", key = "#id")
    public StudentDto findById(final Long id) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        return studentMapper.toDto(student);
    }

    @Transactional(readOnly = true)
    public List<StudentDto> getAllStudents() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> studentMapper.toDto(student)).collect(Collectors.toList());

    }
}
