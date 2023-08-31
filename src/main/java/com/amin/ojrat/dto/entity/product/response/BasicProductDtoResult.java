package com.amin.ojrat.dto.entity.product.response;

import com.amin.ojrat.dto.entity.base.Context;
import jakarta.validation.constraints.NotEmpty;

public class BasicProductDtoResult extends Context {


    @NotEmpty(message = "brandName should not be empty!")
    private String brandName;
    @NotEmpty(message = "productName should not be empty!")
    private String productName;
    @NotEmpty(message = "description should not be empty!")
    private String description;
    @NotEmpty(message = "price should not be empty!")
    private double price;
    @NotEmpty(message = "discount should not be empty!")
    private double discount;
    @NotEmpty(message = "isExist should not be empty!")
    private boolean isExist;

    public BasicProductDtoResult() {
    }

    public BasicProductDtoResult(String brandName, String productName, String description, double price, double discount, boolean isExist) {
        this.brandName = brandName;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.isExist = isExist;
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
}
