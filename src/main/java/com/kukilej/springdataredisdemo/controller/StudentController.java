package com.kukilej.springdataredisdemo.controller;

import com.kukilej.springdataredisdemo.exception.StudentNotFoundException;
import com.kukilej.springdataredisdemo.model.Student;
import com.kukilej.springdataredisdemo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private static final Logger log = LoggerFactory.getLogger(CollegeController.class);

    @GetMapping("/{id}")
    @Cacheable(value = "students", key = "#id")
    public Student getStudentByID(@PathVariable final Long id)  {
        log.info("Get student with id {}", id);
        return studentService.findById(id);
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody final Student student) {
        log.info("Create student with id {}", student.getId());
        return studentService.createStudent(student);
    }

    @PutMapping("/update")
    @CachePut(value = "students", key = "#student.getId()")
    public Student updateStudentByID(@RequestBody final Student student) {
        log.info("Update student with id {}", student.getId());
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "students", key = "#id")
    public Student deleteStudentByID(@PathVariable final Long id)  {
        log.info("Delete student with id {}", id);
        return studentService.deleteStudent(id);
    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/clearCache")
    @CacheEvict(value = "students", allEntries = true)
    public String clearCache() {
        log.info("Clear caches");
        return "All caches cleared";
    }

}
