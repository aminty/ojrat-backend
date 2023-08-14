package com.amin.ojrat.dto.entity.order;

import com.amin.ojrat.dto.entity.product.ProductDto;
import com.amin.ojrat.dto.entity.service.ServiceDto;
import com.amin.ojrat.enums.OrderStatus;
import com.amin.ojrat.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;



public class OrderDto {

    private String description;

    private ServiceDto expert;

    private List<ServiceDto> services;

    private List<ProductDto> products;

    private OrderStatus status;

   private PaymentType paymentType;


    public OrderDto() {
    }

    public OrderDto(String description, ServiceDto expert,
                    List<ServiceDto> services,
                    List<ProductDto> products,
                    OrderStatus status,
                    PaymentType paymentType) {
        this.description = description;
        this.expert = expert;
        this.services = services;
        this.products = products;
        this.status = status;
        this.paymentType = paymentType;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
