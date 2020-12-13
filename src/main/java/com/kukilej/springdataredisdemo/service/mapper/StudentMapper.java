package com.kukilej.springdataredisdemo.service.mapper;

import com.kukilej.springdataredisdemo.domain.dto.StudentDto;
import com.kukilej.springdataredisdemo.domain.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ApplyMapper.class)
public interface StudentMapper {
    StudentDto toDto(Student student);

    Student fromDto(StudentDto dto);
}
