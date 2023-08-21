package com.amin.ojrat.repository;

import com.amin.ojrat.entity.ExpertBranchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IExpertBranchRepository extends JpaRepository<ExpertBranchRequest,Long> {


    Long countByExpert_Id(Long ExpertId);

    List<ExpertBranchRequest> findExpertBranchRequestsByExpert_Id(Long expert_id);

}
