package com.amin.ojrat.repository;

import com.amin.ojrat.entity.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBranchRepository extends JpaRepository<Branch,Long> {

    Page<Branch> findAllByStatusTrue(Pageable pageable);

}
