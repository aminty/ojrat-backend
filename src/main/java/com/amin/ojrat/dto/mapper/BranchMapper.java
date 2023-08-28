package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.branch.response.BasicBranchDtoResult;
import com.amin.ojrat.entity.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {


    BasicBranchDtoResult branchToBasicBranchDto(Branch branch);

}