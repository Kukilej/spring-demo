package com.kukilej.springdataredisdemo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto implements Serializable {

    private Long id;
    private String studentName;
    private Long gpa;
    private Long highSchoolSize;
    private List<ApplyDto> applyList;

}
