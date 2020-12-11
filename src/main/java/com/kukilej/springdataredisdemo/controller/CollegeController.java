package com.kukilej.springdataredisdemo.controller;

import com.kukilej.springdataredisdemo.model.College;
import com.kukilej.springdataredisdemo.service.CollegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/college")
public class CollegeController {

    private CollegeService collegeService;

    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    private static final Logger log = LoggerFactory.getLogger(CollegeController.class);

    @GetMapping("/{id}")
    @Cacheable(value = "colleges", key = "#id")
    public College getCollegeByID(@PathVariable final String id)  {
        log.info("Get college with id {}", id);
        return collegeService.findById(id);
    }

    @PostMapping("/create")
    public College addCollege(@RequestBody final College college) {
        log.info("Create post with id {}", college.getCollegeName());
        return collegeService.createCollege(college);
    }

    @PutMapping("/update")
    @CachePut(value = "colleges", key = "#college.getCollegeName()")
    public College updateCollege(@RequestBody final College college)  {
        log.info("Update college with name {}", college.getCollegeName());
        return collegeService.updateCollege(college);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "colleges", key = "#id")
    public College deleteCollege(@PathVariable final String id) {
        log.info("Delete college with id {}", id);
        return collegeService.deleteCollege(id);
    }

    @GetMapping("/all")
    public List<College> getAllColleges() {
        return collegeService.findAll();
    }

    @GetMapping("/clearCache")
    @CacheEvict(value = "colleges", allEntries = true)
    public String clearCache() {
        log.info("Clear caches");
        return "All caches cleared";
    }
}