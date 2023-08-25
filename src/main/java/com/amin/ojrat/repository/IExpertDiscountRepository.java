package com.amin.ojrat.repository;

import com.amin.ojrat.entity.ExpertDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface IExpertDiscountRepository extends JpaRepository<ExpertDiscount,Long> {

    boolean existsByBranch_IdAndExpert_Id(Long branchId,Long expertId);

}
