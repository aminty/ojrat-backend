package com.amin.ojrat.dto.entity.product.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductCreationDto {


    @NotBlank(message = "brand name should not be empty")
    private String brandName;

    @NotBlank(message = "product name name should not be empty")
    private String productName;

    private String description;

    @NotNull(message = "price name should not be empty")
    @Min(value = 0,message = "price should not be less than 0")
    private double price;

    @Min(value = 0,message = "discount should not be less than 0")
    @Max(value = 100,message = "discount should not be greater than 100")
    private double discount;

    private boolean isExist;

    @NotNull(message = "branch should not be null")
    private Long branchId;

    private byte[] image;

    public ProductCreationDto() {
    }

    public ProductCreationDto(String brandName,
                              String productName,
                              String description,
                              double price,
                              double discount,
                              boolean isExist,
                              Long branchId,
                              byte[] image) {
        this.brandName = brandName;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.isExist = isExist;
        this.branchId = branchId;
        this.image = image;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
