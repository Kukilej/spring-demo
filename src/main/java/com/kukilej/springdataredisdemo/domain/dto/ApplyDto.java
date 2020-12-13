package com.kukilej.springdataredisdemo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor
public class ApplyDto implements Serializable {

    private Long id;
    private Long student;
    private String college;
    private String major;
    private String decision;

}
