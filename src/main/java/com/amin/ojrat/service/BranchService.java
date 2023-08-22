package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.branch.request.BranchInfoModificationDto;
import com.amin.ojrat.dto.entity.product.ProductCreationDto;
import com.amin.ojrat.dto.entity.product.ProductModificationDto;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BranchService {

    void saveProductToBranch( ProductCreationDto param) throws Exception;

    boolean isBranchFullyRegistered(Branch branch);

    void editBranchEditInfo(BranchInfoModificationDto param);

    void editProduct(ProductModificationDto param) throws Exception;

    Product applyNewChange(Product productWithIncomingChange, Product existProduct);

    void removeProduct(Long id) throws Exception;

    Branch findBranchById(Long id);

    Page<Branch> findAllBranchByStatusTrue(Pageable pageable);
}
