package com.amin.ojrat.dto.entity.ExBrReq.request;

import jakarta.validation.constraints.NotNull;

public class ExpBrActivationDtoParam {

    @NotNull(message = "id should not be null")
    Long id;

    boolean status;

    public ExpBrActivationDtoParam() {
    }

    public ExpBrActivationDtoParam(Long id, boolean status) {
        this.id=id;
        this.status = status;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
