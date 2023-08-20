package com.amin.ojrat.dto.entity.product;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductModificationDto {
    @NotNull(message = "id should not be null")
    private Long id;

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

    private byte[] image;

    public ProductModificationDto() {
    }

    public ProductModificationDto(Long id, String brandName,
                                  String productName,
                                  String description, double price,
                                  double discount,
                                  boolean isExist,
                                  byte[] image) {
        this.id = id;
        this.brandName = brandName;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.isExist = isExist;
        this.image=image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
