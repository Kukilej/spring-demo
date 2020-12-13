package com.kukilej.springdataredisdemo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor
public class CollegeDto implements Serializable {

    private String collegeName;
    private String state;
    private int enrollment;
    private List<ApplyDto> applyList;

}
