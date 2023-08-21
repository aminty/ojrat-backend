package com.amin.ojrat.dto.payamak.send;

import lombok.*;
import lombok.experimental.FieldDefaults;


public class SendSmsResult {

    private String recId;

    private String status;

    public SendSmsResult() {
    }

    public SendSmsResult(String recId, String status) {
        this.recId = recId;
        this.status = status;
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String reqId) {
        this.recId = reqId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SendSmsResult{" +
                "recId='" + recId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
