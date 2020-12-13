package com.kukilej.springdataredisdemo.service;

import com.kukilej.springdataredisdemo.domain.dto.ApplyDto;
import com.kukilej.springdataredisdemo.domain.dto.CollegeDto;
import com.kukilej.springdataredisdemo.domain.dto.StudentDto;
import com.kukilej.springdataredisdemo.domain.model.College;
import com.kukilej.springdataredisdemo.domain.model.Student;
import com.kukilej.springdataredisdemo.exception.ApplicationNotFoundException;
import com.kukilej.springdataredisdemo.domain.model.Apply;
import com.kukilej.springdataredisdemo.exception.CollegeNotFoundException;
import com.kukilej.springdataredisdemo.exception.StudentNotFoundException;
import com.kukilej.springdataredisdemo.repository.ApplyRepository;
import com.kukilej.springdataredisdemo.repository.CollegeRepository;
import com.kukilej.springdataredisdemo.repository.StudentRepository;
import com.kukilej.springdataredisdemo.service.mapper.ApplyMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class ApplyService {

    private final ApplyRepository applyRepository;
    private final ApplyMapper applyMapper;
    private final StudentService studentService;
    private final CollegeService collegeService;
    private final StudentRepository studentRepository;
    private final CollegeRepository collegeRepository;

    public ApplyService(ApplyRepository applyRepository, ApplyMapper applyMapper, StudentService studentService, CollegeService collegeService, StudentRepository studentRepository, CollegeRepository collegeRepository) {
        this.applyRepository = applyRepository;
        this.applyMapper = applyMapper;
        this.studentService = studentService;
        this.collegeService = collegeService;
        this.studentRepository = studentRepository;
        this.collegeRepository = collegeRepository;
    }

    public ApplyDto save(final ApplyDto applyDto) {

        Student student = studentRepository.findById(applyDto.getStudent()).orElseThrow(()-> new StudentNotFoundException(applyDto.getStudent()));
        College college = collegeRepository.findById(applyDto.getCollege()).orElseThrow(()->new CollegeNotFoundException(applyDto.getCollege()));
        Apply apply = applyMapper.fromDto(applyDto);

        if (applyDto.getId() != null) {
            apply.setId(applyDto.getId());
        }
        apply.setCollege(college);
        apply.setStudent(student);
        apply = applyRepository.save(apply);
        return applyMapper.toDto(apply);

    }
    @CachePut(value = "applications", key = "#applyDto.getId()")
    public ApplyDto updateApplication (final ApplyDto applyDto)  {
        applyRepository.findById(applyDto.getId()).orElseThrow(() -> new ApplicationNotFoundException(applyDto.getId()));
        return save(applyDto);
    }

    @CacheEvict(value = "applications", key = "#id")
    public Apply deleteApplication(final Long id) {
        Apply apply = applyRepository.findById(id).orElseThrow(() -> new ApplicationNotFoundException(id));
        applyRepository.delete(apply);
        return apply;
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "applications", key = "#id")
    public ApplyDto findById(final Long id)  {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        Apply apply = applyRepository.findById(id).orElseThrow(() -> new ApplicationNotFoundException(id));
        return applyMapper.toDto(apply);
    }
    @Transactional(readOnly = true)
    public List<ApplyDto> findAll() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        List<Apply> applies = applyRepository.findAll();
        return applies.stream().map(apply -> applyMapper.toDto(apply)).collect(Collectors.toList());
    }

}
