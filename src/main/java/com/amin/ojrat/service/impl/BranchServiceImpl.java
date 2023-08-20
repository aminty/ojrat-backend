package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.branch.BranchInfoModificationDto;
import com.amin.ojrat.dto.entity.product.ProductCreationDto;
import com.amin.ojrat.dto.entity.product.ProductModificationDto;
import com.amin.ojrat.dto.mapper.ProductMapper;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Product;
import com.amin.ojrat.exception.MissingIdParameter;
import com.amin.ojrat.exception.NotFullyRegisteredException;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.BranchService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    private final DaoRepositories daoRepositories;
    private final ProductMapper productMapper;

    @Autowired
    public BranchServiceImpl(DaoRepositories daoRepositories, ProductMapper productMapper) {
        this.daoRepositories = daoRepositories;
        this.productMapper = productMapper;
    }

    @Override
    public void saveProductToBranch(ProductCreationDto param) throws NotFullyRegisteredException {
        Optional<Branch> findBranchById = daoRepositories.getBranchRepository().findById(param.getBranchId());

        if (findBranchById.isPresent()) {
            Product product = productMapper.productDtoToProduct(param);
            Branch branch = findBranchById.get();
            branch.getProducts().add(product);
            product.setBranch(branch);
            if (isBranchFullyRegistered(branch))
                daoRepositories.getBranchRepository().save(branch);
            else throw new NotFullyRegisteredException("your store is not complete registered.");
        } else {
            throw new EntityNotFoundException("Branch not found.");
        }

    }

    @Override
    public boolean isBranchFullyRegistered(Branch branch) {
        if (branch.getDescription() == null
                || branch.getLocation() == null
                || branch.getName() == null
                || branch.getPhone() == null)
            return false;
        else
            return true;
    }

    @Override
    public void editBranchEditInfo(BranchInfoModificationDto param) {
        Optional<Branch> foundBranch = daoRepositories.getBranchRepository().findById(param.getId());
        if (foundBranch.isPresent()) {
            Branch existBranch = foundBranch.get();
            existBranch.setDescription(param.getDescription());
            existBranch.setName(param.getName());
            existBranch.setLocation(param.getLocation());
            existBranch.setPhone(param.getPhone());
            daoRepositories.getBranchRepository().save(existBranch);
        } else {
            throw new EntityNotFoundException("branch not found.");
        }
    }

    @Override
    public void editProduct(ProductModificationDto param) throws Exception {
        if (param.getId() != null) {
            Optional<Product> foundProduct = daoRepositories.getProductRepository().findById(param.getId());
            if (foundProduct.isPresent()) {
                Product productWithIncomingChange = productMapper.productDtoToProduct(param);
                Product existProduct = foundProduct.get();

                daoRepositories.getProductRepository().save(applyNewChange(productWithIncomingChange, existProduct));
            } else {
                throw new EntityNotFoundException("Product not found.");
            }
        } else {
            throw new MissingIdParameter("id parameter is null");
        }
    }

    @Override
    public Product applyNewChange(Product productWithIncomingChange, Product existProduct) {

        existProduct.setBrandName(productWithIncomingChange.getBrandName());
        existProduct.setProductName(productWithIncomingChange.getProductName());
        existProduct.setDescription(productWithIncomingChange.getDescription());
        existProduct.setPrice(productWithIncomingChange.getPrice());
        existProduct.setDiscount(productWithIncomingChange.getDiscount());
        existProduct.setExist(productWithIncomingChange.isExist());
        existProduct.setImage(productWithIncomingChange.getImage());
        return existProduct;
    }

    @Override
    public void removeProduct(Long id) throws Exception {
        if (id != null) {
            if (daoRepositories.getProductRepository().findById(id).isPresent())
                daoRepositories.getProductRepository().deleteById(id);
            else throw new EntityNotFoundException("product not found");
        } else {
            throw new MissingIdParameter("id parameter is null");
        }
    }

    @Override
    public Branch findBranchById(Long id) {
        Optional<Branch> found = daoRepositories.getBranchRepository().findById(id);
        if (found.isPresent())
            return found.get();
        throw new EntityNotFoundException("branch not found exception");

    }



}
