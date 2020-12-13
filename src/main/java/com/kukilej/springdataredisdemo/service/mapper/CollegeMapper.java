package com.kukilej.springdataredisdemo.service.mapper;

import com.kukilej.springdataredisdemo.domain.dto.CollegeDto;
import com.kukilej.springdataredisdemo.domain.model.College;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ApplyMapper.class)
public interface CollegeMapper {
    CollegeDto toDto(College college);

    College fromDto(CollegeDto dto);
}