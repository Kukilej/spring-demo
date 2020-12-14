package com.kukilej.springdataredisdemo.service.mapper;

import com.kukilej.springdataredisdemo.domain.dto.StudentDto;
import com.kukilej.springdataredisdemo.domain.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ApplyMapper.class)
public interface StudentMapper {

    StudentDto toDto(Student student);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "applyList", ignore = true)
    Student fromDto(StudentDto dto);
}
