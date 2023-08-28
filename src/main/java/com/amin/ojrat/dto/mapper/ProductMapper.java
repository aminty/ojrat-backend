package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.product.request.ProductCreationDto;
import com.amin.ojrat.dto.entity.product.request.ProductModificationDto;
import com.amin.ojrat.dto.entity.product.response.BasicProductDto;
import com.amin.ojrat.entity.Product;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(source = "branchId",target = "branch.id")
    Product productDtoToProduct(ProductCreationDto param);


    Product productDtoToProduct(ProductModificationDto param);


    BasicProductDto productToBasicProductDto(Product product);


}
