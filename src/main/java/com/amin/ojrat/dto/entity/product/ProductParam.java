package com.amin.ojrat.dto.entity.product;

import com.amin.ojrat.entity.Branch;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductParam {

    Long id;

    @NotBlank(message = "brand name should not be empty")
    String brandName;

    @NotBlank(message = "product name name should not be empty")
    String productName;

    String description;

    @NotNull(message = "price name should not be empty")
    double price;

    double discount;

    @NotNull(message = "existence should not be empty")
    boolean isExist;

    @NotNull(message = "branch name should not be empty")
    Branch branch;

}
