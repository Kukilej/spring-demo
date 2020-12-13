package com.kukilej.springdataredisdemo.controller;

import com.kukilej.springdataredisdemo.domain.dto.StudentDto;
import com.kukilej.springdataredisdemo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    private static final Logger log = LoggerFactory.getLogger(CollegeController.class);

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> get(@PathVariable final Long id)  {
        log.info("Get student with id {}", id);
        StudentDto studentDto =  studentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(studentDto);
    }

    @PostMapping
    public ResponseEntity<StudentDto> add(@Valid @RequestBody  StudentDto studentDto) {
        log.info("Create student with id {}", studentDto.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.save(studentDto));
    }

    @PutMapping
    public ResponseEntity<StudentDto> update(@RequestBody final StudentDto studentDto) {
        log.info("Update student with id {}", studentDto.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(studentService.updateStudent(studentDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> delete(@PathVariable final Long id)  {
        log.info("Delete student with id {}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping
    public ResponseEntity<Collection<StudentDto>> getAll() {
        List<StudentDto> students = new ArrayList<>(studentService.getAllStudents());
        return ResponseEntity.status(HttpStatus.OK)
                .body(students);
    }

    @GetMapping("/clearCache")
    @CacheEvict(value = "students", allEntries = true)
    public String clearCache() {
        log.info("Clear caches");
        return "All caches cleared";
    }

}
