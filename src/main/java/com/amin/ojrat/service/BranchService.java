package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.branch.BranchInfoModificationDto;
import com.amin.ojrat.dto.entity.product.ProductCreationDto;
import com.amin.ojrat.dto.entity.product.ProductModificationDto;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Product;

public interface BranchService {

    void saveProductToBranch( ProductCreationDto param) throws Exception;

    boolean isBranchFullyRegistered(Branch branch);

    void editBranchEditInfo(BranchInfoModificationDto param);

    void editProduct(ProductModificationDto param) throws Exception;

    Product applyNewChange(Product productWithIncomingChange, Product existProduct);

    void removeProduct(Long id) throws Exception;
}
