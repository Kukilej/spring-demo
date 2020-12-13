package com.kukilej.springdataredisdemo.repository;

import com.kukilej.springdataredisdemo.domain.model.Apply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<Apply, Long> {


}
