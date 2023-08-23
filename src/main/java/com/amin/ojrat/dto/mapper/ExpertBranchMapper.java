package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicResult;
import com.amin.ojrat.entity.ExpertBranchRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExpertBranchMapper {

    @Mapping(target = "branchId",source = "branch.id")
    @Mapping(target = "expertId",source = "expert.id")
    @Mapping(target = "branchName",source = "branch.name")
    @Mapping(target = "expertName",source = "expert.firstName")
    @Mapping(target = "requestStatus",source = "approved")
    @Mapping(target = "expertLastName",source = "expert.lastName")
    ExpBrBasicResult expertBranchToExpBrBasicResult(ExpertBranchRequest param);
}
