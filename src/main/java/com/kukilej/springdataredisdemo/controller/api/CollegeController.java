package com.kukilej.springdataredisdemo.controller.api;

import com.kukilej.springdataredisdemo.domain.dto.CollegeDto;
import com.kukilej.springdataredisdemo.service.CollegeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/college")
@Api("Controller for exposing College operations via REST endpoint.")
public class CollegeController {

    private final CollegeService collegeService;

    public CollegeController(CollegeService collegeService) {
        this.collegeService = collegeService;
    }

    private static final Logger log = LoggerFactory.getLogger(CollegeController.class);

    @GetMapping("/{id}")
    @ApiOperation(value = "Get college by id")
    public ResponseEntity<CollegeDto> get(@PathVariable final String id) {
        log.info("Get college with id {}", id);
        CollegeDto college = collegeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(college);
    }

    @PostMapping
    @ApiOperation(value = "Add new college")
    public ResponseEntity<CollegeDto> add(@RequestBody final CollegeDto collegeDto) {
        log.info("Create post with id {}", collegeDto.getCollegeName());
        return ResponseEntity.status(HttpStatus.CREATED).body(collegeService.save(collegeDto));
    }

    @PutMapping
    @ApiOperation(value = "Update college")
    public ResponseEntity<CollegeDto> update(@RequestBody final CollegeDto collegeDto) {
        log.info("Update college with name {}", collegeDto.getCollegeName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(collegeService.update(collegeDto));

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete college by Id")
    public ResponseEntity<CollegeDto> delete(@PathVariable final String id) {
        log.info("Delete college with id {}", id);
        collegeService.deleteCollege(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping
    @ApiOperation(value = "List all colleges")
    public ResponseEntity<Collection<CollegeDto>> getAll() {
        List<CollegeDto> colleges = new ArrayList<>(collegeService.findAll());
        return ResponseEntity.status(HttpStatus.OK)
                .body(colleges);
    }

    @GetMapping("/clearCache")
    @ApiOperation(value = "Empties cache for colleges")
    @CacheEvict(value = "colleges", allEntries = true)
    public String clearCache() {
        log.info("Clear caches");
        return "All caches cleared";
    }
}