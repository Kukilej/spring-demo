package com.kukilej.springdataredisdemo.repository;

import com.kukilej.springdataredisdemo.domain.model.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, String> {



}

