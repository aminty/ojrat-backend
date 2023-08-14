package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.product.ProductCreationDto;
import com.amin.ojrat.dto.entity.product.ProductModificationDto;
import com.amin.ojrat.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    @Mapping(source = "branchId",target = "branch.id")
    Product productDtoToProduct(ProductCreationDto param);


    Product productDtoToProduct(ProductModificationDto param);

}
