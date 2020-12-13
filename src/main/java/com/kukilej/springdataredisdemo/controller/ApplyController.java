package com.kukilej.springdataredisdemo.controller;

import com.kukilej.springdataredisdemo.domain.dto.ApplyDto;
import com.kukilej.springdataredisdemo.domain.model.Apply;
import com.kukilej.springdataredisdemo.service.ApplyService;
import com.kukilej.springdataredisdemo.service.mapper.ApplyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/apply")
public class ApplyController {

    private final ApplyService applyService;
    public ApplyController(ApplyService applyService){
        this.applyService = applyService;
    }

    private static final Logger log = LoggerFactory.getLogger(com.kukilej.springdataredisdemo.controller.CollegeController.class);

    @GetMapping("/{id}")
    public ResponseEntity<ApplyDto> get(@PathVariable final Long id)  {
        log.info("Get application with id {}", id);
        ApplyDto apply = applyService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(apply);
    }

    @GetMapping
    public ResponseEntity<Collection<ApplyDto>> getAll() {
        List<ApplyDto> applies = new ArrayList<>(applyService.findAll());
        return ResponseEntity.status(HttpStatus.OK)
                .body(applies);


    }

    @PutMapping
    public ResponseEntity<ApplyDto> update(@RequestBody final ApplyDto applyDto) {
        log.info("Update application with id {}", applyDto.getId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(applyService.updateApplication(applyDto));
    }

    @PostMapping
    public  ResponseEntity<ApplyDto> create(@RequestBody final ApplyDto applyDto) {
        log.info("Create application with id {}", applyDto.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(applyService.save(applyDto));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApplyDto> delete(@PathVariable final Long id) {
        log.info("Delete application with id {}", id);
        applyService.deleteApplication(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping("/clearCache")
    @CacheEvict(value = "applications", allEntries = true)
    public String clearCache() {
        log.info("Clear caches");
        return "All caches cleared";
    }
}