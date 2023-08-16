package com.amin.ojrat.dto.payamak.send;

import lombok.*;
import lombok.experimental.FieldDefaults;


public class SendSmsResult {

    private String reqId;

    private String status;

    public SendSmsResult() {
    }

    public SendSmsResult(String reqId, String status) {
        this.reqId = reqId;
        this.status = status;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
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
                "reqId='" + reqId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
