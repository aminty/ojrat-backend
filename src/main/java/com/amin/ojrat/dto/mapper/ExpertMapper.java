package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.expert.request.ExpertCreationDto;
import com.amin.ojrat.entity.Expert;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpertMapper {

    Expert expertCreationDtoToExpert(ExpertCreationDto param);

}
