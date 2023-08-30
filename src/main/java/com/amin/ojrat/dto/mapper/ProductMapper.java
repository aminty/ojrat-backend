package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.product.request.ProductCreationDtoParam;
import com.amin.ojrat.dto.entity.product.request.ProductModificationDtoParam;
import com.amin.ojrat.dto.entity.product.response.BasicProductDtoResult;
import com.amin.ojrat.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(source = "branchId",target = "branch.id")
    Product productDtoToProduct(ProductCreationDtoParam param);


    Product productDtoToProduct(ProductModificationDtoParam param);


    BasicProductDtoResult productToBasicProductDto(Product product);


}
