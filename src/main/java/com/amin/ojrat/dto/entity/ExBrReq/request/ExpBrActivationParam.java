package com.amin.ojrat.dto.entity.ExBrReq.request;

import jakarta.validation.constraints.NotNull;

public class ExpBrActivationParam {

    @NotNull(message = "id should not be null")
    Long id;

    boolean status;

    public ExpBrActivationParam() {
    }

    public ExpBrActivationParam( Long id,boolean status) {
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
