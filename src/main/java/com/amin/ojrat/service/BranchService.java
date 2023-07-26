package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.product.ProductParam;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.exception.MissingIdParameter;
import com.amin.ojrat.exception.NotFullyRegistredException;

public interface BranchService {

    void saveProductToBranch( ProductParam param) throws Exception;

    boolean isBranchFullyRegistered(Branch branch);

    void editProduct(ProductParam param) throws Exception;

    void removeProduct(Long id) throws Exception;
}
