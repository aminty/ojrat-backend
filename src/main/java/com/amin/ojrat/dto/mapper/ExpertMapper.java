package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.expert.request.ExpertCreationDtoParam;
import com.amin.ojrat.entity.Expert;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExpertMapper {

    Expert expertCreationDtoToExpert(ExpertCreationDtoParam param);

}
