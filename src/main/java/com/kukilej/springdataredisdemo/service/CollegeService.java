package com.kukilej.springdataredisdemo.service;

import com.kukilej.springdataredisdemo.exception.ApplicationNotFoundException;
import com.kukilej.springdataredisdemo.exception.CollegeNotFoundException;
import com.kukilej.springdataredisdemo.model.College;
import com.kukilej.springdataredisdemo.repository.CollegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CollegeService {


    CollegeRepository collegeRepository;

    public CollegeService(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    public College createCollege(final College college) {
        return collegeRepository.save(college);
    }

    public College updateCollege (final College college) throws CollegeNotFoundException {
        collegeRepository.findById(college.getCollegeName()).orElseThrow(() -> new CollegeNotFoundException(college.getCollegeName()));
        return collegeRepository.save(college);
    }

    public College deleteCollege(final String id) throws CollegeNotFoundException {
        College college = collegeRepository.findById(id).orElseThrow(() -> new CollegeNotFoundException(id));
        collegeRepository.delete(college);
        return college;
    }


    public College findById(final String collegeId) throws CollegeNotFoundException {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return collegeRepository.findById(collegeId).orElseThrow(() -> new CollegeNotFoundException(collegeId));
    }

    public List<College> findAll() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return collegeRepository.findAll();
    }
}