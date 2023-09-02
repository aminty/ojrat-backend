package com.amin.ojrat.dto.entity.ticket.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SendNewMessageDtoParam {

    @NotNull(message = "ticketId should not be null")
    private Long ticketId;

    @NotEmpty(message =     "message should not be empty")
    private String message;

    @NotNull(message = "senderId should not be null")
    private Long senderId;

    public SendNewMessageDtoParam() {
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
}
