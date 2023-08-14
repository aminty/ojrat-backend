package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.branch.BranchInfoModificationDto;
import com.amin.ojrat.entity.Branch;

public interface BranchMapper {


    Branch  branchInfoDtoToBranch(BranchInfoModificationDto param);



}
