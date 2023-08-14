package com.amin.ojrat.dto.entity.branch;

import com.amin.ojrat.dto.entity.ExBrReq.ExpertBranchDto;
import com.amin.ojrat.dto.entity.admin.AdminDto;
import com.amin.ojrat.dto.entity.expert.ExpertDto;
import com.amin.ojrat.dto.entity.product.ProductDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;



public class BranchDto {

    @NotNull(message = "branch id should not be null")
    private Long id;

    private String name;

    private String location;

    private String phone;

    private String description;

    private AdminDto admin;

    private List<ExpertDto> experts ;

    private List<ProductDto> products;

    private List<ExpertBranchDto> requests;

    private boolean status;

    public BranchDto() {
    }

    public BranchDto(Long id, String name, String location, String phone,
                     String description,
                     AdminDto admin, List<ExpertDto> experts,
                     List<ProductDto> products,
                     List<ExpertBranchDto> requests,
                     boolean status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.description = description;
        this.admin = admin;
        this.experts = experts;
        this.products = products;
        this.requests = requests;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AdminDto getAdmin() {
        return admin;
    }

    public void setAdmin(AdminDto admin) {
        this.admin = admin;
    }

    public List<ExpertDto> getExperts() {
        return experts;
    }

    public void setExperts(List<ExpertDto> experts) {
        this.experts = experts;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public List<ExpertBranchDto> getRequests() {
        return requests;
    }

    public void setRequests(List<ExpertBranchDto> requests) {
        this.requests = requests;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
