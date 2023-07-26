package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.product.ProductParam;
import com.amin.ojrat.entity.Product;

public interface IProductMapper {

    Product productParamToProduct(ProductParam Param);

    ProductParam productToProductParam(Product product);

}
