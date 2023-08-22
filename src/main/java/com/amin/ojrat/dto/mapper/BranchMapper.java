package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.branch.response.BasicBranchDto;
import com.amin.ojrat.entity.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchMapper {


    BasicBranchDto branchToBasicBranchDto(Branch branch);

}