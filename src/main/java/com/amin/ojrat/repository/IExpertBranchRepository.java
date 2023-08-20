package com.amin.ojrat.repository;

import com.amin.ojrat.entity.ExpertBranchRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExpertBranchRepository extends JpaRepository<ExpertBranchRequest,Long> {
}
