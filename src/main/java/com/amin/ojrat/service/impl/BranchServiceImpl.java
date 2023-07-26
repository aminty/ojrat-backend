package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.product.ProductParam;
import com.amin.ojrat.dto.mapper.IProductMapper;
import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Product;
import com.amin.ojrat.exception.MissingIdParameter;
import com.amin.ojrat.exception.NotFullyRegistredException;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.BranchService;
import jakarta.persistence.EntityNotFoundException;
import org.bouncycastle.i18n.MissingEntryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.invoke.MissingParametersException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {

    private final DaoRepositories daoRepositories;
    private final IProductMapper productMapper;

    @Autowired
    public BranchServiceImpl(DaoRepositories daoRepositories, IProductMapper productMapper) {
        this.daoRepositories = daoRepositories;
        this.productMapper = productMapper;
    }

    @Override
    public void saveProductToBranch( ProductParam param) throws NotFullyRegistredException {
        Optional<Branch> findBranchById = daoRepositories.getBranchRepository().findById(param.getBranch().getId());
        Product product = productMapper.productParamToProduct(param);

        if (findBranchById.isPresent()) {
            Branch branch = findBranchById.get();
            branch.getProducts().add(product);
            product.setBranch(branch);
            if (isBranchFullyRegistered(branch))
               daoRepositories.getBranchRepository().save(branch);
            else throw new NotFullyRegistredException("your store is not complete registered.");
        } else {
            throw new EntityNotFoundException("Branch not found.");
        }

    }

    @Override
    public boolean isBranchFullyRegistered(Branch branch) {
        if (branch.getDescription()==null
                || branch.getLocation()==null
                || branch.getName()==null
                || branch.getPhone()==null)
            return false;
        else
            return true;
    }

    @Override
    public void editProduct(ProductParam param) throws Exception {
        if (param.getId()!=null) {
            Optional<Product> foundProduct = daoRepositories.getProductRepository().findById(param.getId());
            if (foundProduct.isPresent()){
                Product product = productMapper.productParamToProduct(param);
                daoRepositories.getProductRepository().save(product);
            }else{
                throw new EntityNotFoundException("Product not found.");
            }
        }else{
            throw new MissingIdParameter("id parameter is null");
        }
    }

    @Override
    public void removeProduct(Long id) throws Exception {
        if (id!=null) {
            if (daoRepositories.getProductRepository().findById(id).isPresent())
                    daoRepositories.getProductRepository().deleteById(id);
            else throw new EntityNotFoundException("product not found");
        }else{
            throw new MissingIdParameter("id parameter is null");
            }
        }

}
