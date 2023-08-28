package com.amin.ojrat.repository;

import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IBranchRepository extends JpaRepository<Branch,Long> {

    Page<Branch> findAllByStatusTrue(Pageable pageable);

    boolean existsBranchByUniqueName(String uniqueName);

    @EntityGraph(attributePaths = {"products"})
    @Query("select b.products FROM Branch b WHERE  b.Id= :branchId")
    Page<Product> findAllProductWithBranchId(Long branchId,Pageable pageable);
}
