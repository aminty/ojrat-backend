package com.amin.ojrat.dto.mapper.impl;

import com.amin.ojrat.dto.entity.product.ProductParam;
import com.amin.ojrat.dto.mapper.IProductMapper;
import com.amin.ojrat.entity.Product;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger2.mappers.PropertyMapperImpl;
@Component
public class ProductMapperImpl implements IProductMapper {
    @Override
    public Product productParamToProduct(ProductParam param) {
        Product product=new Product();
        if (param.getId()!=null)
            product.setId(param.getId());
        product.setProductName(param.getProductName());
        product.setBrandName(param.getBrandName());
        product.setBranch(param.getBranch());
        product.setDiscount(param.getDiscount());
        product.setPrice(param.getPrice());
        product.setDescription(param.getDescription());
        product.setExist(param.isExist());
        return product;
    }

    @Override
    public ProductParam productToProductParam(Product product) {
        ProductParam param=new ProductParam();
        if (product.getId()!=null)
            param.setId(product.getId());
        param.setProductName(product.getProductName());
        param.setBrandName(product.getBrandName());
        param.setDescription(product.getDescription());
        param.setPrice(product.getPrice());
        param.setDiscount(product.getDiscount());
        param.setExist(product.isExist());
        param.setBranch(product.getBranch());
        return param;
    }
}
