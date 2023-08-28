package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrActivationDtoParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicDtoResult;
import com.amin.ojrat.dto.entity.branch.request.BranchInfoModificationDtoParam;
import com.amin.ojrat.dto.entity.branch.request.ChangeDiscountDtoParam;
import com.amin.ojrat.dto.entity.product.request.ProductCreationDto;
import com.amin.ojrat.dto.entity.product.request.ProductModificationDto;
import com.amin.ojrat.dto.entity.product.response.BasicProductDto;
import com.amin.ojrat.dto.mapper.ProductMapper;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.entity.ExpertDiscount;
import com.amin.ojrat.entity.Product;
import com.amin.ojrat.exception.ChangeStatusException;
import com.amin.ojrat.exception.DeletionException;
import com.amin.ojrat.exception.NotFullyRegisteredException;
import com.amin.ojrat.exception.UniqueNameException;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.BranchService;
import com.amin.ojrat.service.ExpertBranchRequestService;
import com.amin.ojrat.service.ExpertDiscountService;
import com.amin.ojrat.service.ExpertService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RelationNotFoundException;
import java.util.List;
import java.util.Objects;

@Service
public class BranchServiceImpl implements BranchService {

    private final DaoRepositories daoRepositories;
    private final ProductMapper productMapper;
    private final ExpertBranchRequestService expertBranchRequestService;
    private final ExpertService expertService;
    private final ExpertDiscountService discountService;


    @Autowired
    public BranchServiceImpl(DaoRepositories daoRepositories,
                             ProductMapper productMapper,
                             ExpertBranchRequestService expertBranchRequestService, ExpertService expertService, ExpertDiscountService discountService) {
        this.daoRepositories = daoRepositories;
        this.productMapper = productMapper;
        this.expertBranchRequestService = expertBranchRequestService;
        this.expertService = expertService;
        this.discountService = discountService;
    }

    @Override
    public void saveProductToBranch(ProductCreationDto param) throws NotFullyRegisteredException {
        Branch branch = findBranchById(param.getBranchId());

        Product product = productMapper.productDtoToProduct(param);
        branch.getProducts().add(product);
        product.setBranch(branch);

        if (isBranchFullyRegistered(branch)) {
            daoRepositories.getBranchRepository().save(branch);
        } else {
            throw new NotFullyRegisteredException("Your store is not completely registered.");
        }
    }

    @Override
    public void editBranchEditInfo(BranchInfoModificationDtoParam param) throws UniqueNameException {
        Branch existBranch = findBranchById(param.getId());
        if (!Objects.equals(param.getUniqueName(), existBranch.getUniqueName()))
            checkUniqueNameAndThrow(param.getUniqueName());
        updateBranchInfo(existBranch, param);
        daoRepositories.getBranchRepository().save(existBranch);
    }

    @Override
    public void editProduct(ProductModificationDto param) throws Exception {
        Product existProduct = findProductByIdOrThrow(param.getId(), "Product not found.");

        Product updatedProduct = productMapper.productDtoToProduct(param);
        applyNewChange(updatedProduct, existProduct);

        daoRepositories.getProductRepository().save(existProduct);
    }

    @Override
    public void removeProduct(Long id) throws Exception {
        findProductByIdOrThrow(id, "Product not found");
        daoRepositories.getProductRepository().deleteById(id);
    }

    @Override
    public Branch findBranchById(Long id) {
        return daoRepositories.getBranchRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found exception"));
    }

    @Override
    public Page<Branch> findAllBranchByStatusTrue(Pageable pageable) {
        return daoRepositories.getBranchRepository().findAllByStatusTrue(pageable);
    }

    @Override
    public List<ExpBrBasicDtoResult> getAllJoinRequest(Long branchId) {
        if (daoRepositories.getBranchRepository().existsById(branchId))
            return expertBranchRequestService.findAllRequestByBranchId(branchId);
        else throw new EntityNotFoundException("branch not found");
    }

    @Override
    public void deleteRequest(Long requestId) throws DeletionException {
        expertBranchRequestService.deleteRequest(requestId);

    }


    @Override
    @Transactional
    public void removeExpertFromBranch(Long expertId, Long branchId) throws RelationNotFoundException {
        Expert foundExpert = expertService.findExpert(expertId);
        Branch foundBranch = new Branch();
        foundBranch.setId(branchId);
        boolean isRelationExists = foundExpert
                .getBranches()
                .stream()
                .anyMatch(branch -> Objects.equals(branch.getId(), branchId));
        if (isRelationExists){
            foundBranch=findBranchById(branchId);
            foundExpert.getBranches().remove(foundBranch);
            expertService
                    .updateExpert(foundExpert);
            expertBranchRequestService
                    .deleteRequestByIdsInsideExpertRelationDeletionInBranchService(expertId,branchId);
            discountService.deleteDiscountByIds(expertId,branchId);
        }else throw new RelationNotFoundException("no relation found for this expert");

    }

    @Override
    public ExpBrBasicDtoResult changeRequestStatus(ExpBrActivationDtoParam param) throws ChangeStatusException {
        return expertBranchRequestService.changeRequestStatus(param);
    }

    @Override
    public void save(Branch branch) {
        daoRepositories.getBranchRepository().save(branch);
    }

    @Override
    public void changeDiscountPercent(ChangeDiscountDtoParam param) {
        ExpertDiscount expertDiscount = discountService.findExpertDiscount(param);
        expertDiscount.setDiscountPercentage(param.getPercent());
        discountService.updateDiscount(expertDiscount);
    }

    @Override
    @Transactional
    public Page<BasicProductDto> findAvailableProduct(Long branchId, Pageable pageable) {
        Page<Product> allProductWithBranchId = daoRepositories
                .getBranchRepository()
                .findAllProductWithBranchId(branchId, pageable);
        return allProductWithBranchId.map(productMapper::productToBasicProductDto);
    }


    private void checkUniqueNameAndThrow(String uniqueName) throws UniqueNameException {
        if (daoRepositories.getBranchRepository().existsBranchByUniqueName(uniqueName)) {
            throw new UniqueNameException("This unique name is already taken by another store.");
        }
    }

    private Product findProductByIdOrThrow(Long id, String errorMessage) {
        return daoRepositories.getProductRepository().findById(id)
                .orElseThrow(() -> new EntityNotFoundException(errorMessage));
    }

    @Override
    public void applyNewChange(Product updatedProduct, Product existProduct) {
        existProduct.setBrandName(updatedProduct.getBrandName());
        existProduct.setProductName(updatedProduct.getProductName());
        existProduct.setDescription(updatedProduct.getDescription());
        existProduct.setPrice(updatedProduct.getPrice());
        existProduct.setDiscount(updatedProduct.getDiscount());
        existProduct.setExist(updatedProduct.isExist());
        existProduct.setImage(updatedProduct.getImage());
    }

    private void updateBranchInfo(Branch branch, BranchInfoModificationDtoParam param) {
        branch.setUniqueName(param.getUniqueName());
        branch.setDescription(param.getDescription());
        branch.setName(param.getName());
        branch.setLocation(param.getLocation());
        branch.setPhone(param.getPhone());
        branch.setStatus(true);
    }

    @Override
    public boolean isBranchFullyRegistered(Branch branch) {
        return branch.getDescription() != null
                && branch.getLocation() != null
                && branch.getName() != null
                && branch.getPhone() != null
                && branch.getUniqueName() != null;
    }
}
