package com.kukilej.springdataredisdemo.service;

import com.kukilej.springdataredisdemo.domain.dto.CollegeDto;
import com.kukilej.springdataredisdemo.exception.CollegeNotFoundException;
import com.kukilej.springdataredisdemo.domain.model.College;
import com.kukilej.springdataredisdemo.repository.CollegeRepository;
import com.kukilej.springdataredisdemo.service.mapper.CollegeMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CollegeService {

    private final CollegeMapper collegeMapper;
    private final CollegeRepository collegeRepository;

    public CollegeService(CollegeMapper collegeMapper, CollegeRepository collegeRepository) {
        this.collegeMapper = collegeMapper;
        this.collegeRepository = collegeRepository;
    }

    public CollegeDto save(final CollegeDto collegeDto) {
        College college = collegeMapper.fromDto(collegeDto);
        college = collegeRepository.save(college);
        return collegeMapper.toDto(college);
    }

    @CachePut(value = "colleges", key = "#collegeDto.getCollegeName()")
    public CollegeDto update(final CollegeDto collegeDto) {
        collegeRepository.findById(collegeDto.getCollegeName()).orElseThrow(() -> new CollegeNotFoundException(collegeDto.getCollegeName()));
        return save(collegeDto);
    }

    @CacheEvict(value = "colleges", key = "#id")
    public void deleteCollege(final String id) {
        College college = collegeRepository.findById(id).orElseThrow(() -> new CollegeNotFoundException(id));
        collegeRepository.delete(college);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "colleges", key = "#id")
    public CollegeDto findById(final String id)  {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        College college = collegeRepository.findById(id).orElseThrow(() -> new CollegeNotFoundException(id));
        return collegeMapper.toDto(college);
    }

    @Transactional(readOnly = true)
    public List<CollegeDto> findAll() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        List<College> colleges = collegeRepository.findAll();
        return colleges.stream().map(college -> collegeMapper.toDto(college)).collect(Collectors.toList());

    }
}