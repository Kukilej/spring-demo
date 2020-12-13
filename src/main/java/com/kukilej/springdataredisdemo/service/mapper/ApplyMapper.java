package com.kukilej.springdataredisdemo.service.mapper;

import com.kukilej.springdataredisdemo.domain.dto.ApplyDto;
import com.kukilej.springdataredisdemo.domain.model.Apply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ApplyMapper {

    @Mapping(source = "student.id", target = "student")
    @Mapping(source = "college.collegeName", target = "college")
    ApplyDto toDto(Apply apply);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "college", ignore = true)
    Apply fromDto(ApplyDto applyDto);
}
