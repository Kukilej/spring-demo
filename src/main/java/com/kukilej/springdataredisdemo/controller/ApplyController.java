package com.kukilej.springdataredisdemo.controller;

import com.kukilej.springdataredisdemo.model.Apply;
import com.kukilej.springdataredisdemo.service.ApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apply")
public class ApplyController {

    private ApplyService applyService;

    public ApplyController(ApplyService applyService) {
        this.applyService = applyService;
    }

    private static final Logger log = LoggerFactory.getLogger(com.kukilej.springdataredisdemo.controller.CollegeController.class);

    @GetMapping("/{id}")
    @Cacheable(value = "applications", key = "#id")
    public Apply getCollegeByID(@PathVariable final Long id)  {
        log.info("Get application with id {}", id);
        return applyService.findById(id);
    }

    @GetMapping("/all")
    public List<Apply> getAllApplications() {
        return applyService.findAll();
    }

    @PutMapping("/update")
    @CachePut(value = "applications", key = "#apply.getId()")
    public Apply updateApplicationByID(@RequestBody final Apply apply) {
        log.info("Update application with id {}", apply.getApplyId());
        return applyService.updateApplication(apply);
    }

    @PostMapping("/create")
    public Apply createApplication(@RequestBody final Apply apply) {
        log.info("Create application with id {}", apply.getApplyId());
        return applyService.createApplication(apply);
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(value = "applications", key = "#id")
    public Apply deleteApplicationByID(@PathVariable final Long id) {
        log.info("Delete application with id {}", id);
        return applyService.deleteApplication(id);
    }

    @GetMapping("/clearCache")
    @CacheEvict(value = "applications", allEntries = true)
    public String clearCache() {
        log.info("Clear caches");
        return "All caches cleared";
    }
}