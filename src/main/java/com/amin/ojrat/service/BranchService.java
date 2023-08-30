package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrActivationDtoParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicDtoResult;
import com.amin.ojrat.dto.entity.branch.request.BranchInfoModificationDtoParam;
import com.amin.ojrat.dto.entity.branch.request.ChangeDiscountDtoParam;
import com.amin.ojrat.dto.entity.product.request.ProductCreationDtoParam;
import com.amin.ojrat.dto.entity.product.request.ProductModificationDtoParam;
import com.amin.ojrat.dto.entity.product.response.BasicProductDtoResult;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Product;
import com.amin.ojrat.exception.ChangeStatusException;
import com.amin.ojrat.exception.DeletionException;
import com.amin.ojrat.exception.UniqueNameException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.management.relation.RelationNotFoundException;
import java.util.List;

public interface BranchService {

    void saveProductToBranch( ProductCreationDtoParam param) throws Exception;

    boolean isBranchFullyRegistered(Branch branch);

    void editBranchEditInfo(BranchInfoModificationDtoParam param) throws UniqueNameException;

    void editProduct(ProductModificationDtoParam param) throws Exception;

    void applyNewChange(Product productWithIncomingChange, Product existProduct);

    void removeProduct(Long id) throws Exception;

    Branch findBranchById(Long id);

    Page<Branch> findAllBranchByStatusTrue(Pageable pageable);

    List<ExpBrBasicDtoResult> getAllJoinRequest(Long branchId);

    void deleteRequest(Long requestId) throws DeletionException;

    void removeExpertFromBranch(Long expertId,Long branchId) throws RelationNotFoundException;

    ExpBrBasicDtoResult changeRequestStatus(ExpBrActivationDtoParam param) throws ChangeStatusException;

    void saveBranch(Branch branch);

    boolean isExistsById(Long branchId);

    void changeDiscountPercent(ChangeDiscountDtoParam param);

    Page<BasicProductDtoResult> findAvailableProduct(Long branchId, Pageable pageable);

}
