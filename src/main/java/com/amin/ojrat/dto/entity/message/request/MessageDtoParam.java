package com.amin.ojrat.dto.entity.message.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class MessageDtoParam {

    @NotNull(message = "sender id should not be null")
    Long senderId;


    @NotNull(message = "receiver id should not be null")
    Long receiverId;

    @NotEmpty(message = "message should not be empty")
    String message;

    public MessageDtoParam() {
    }

    public MessageDtoParam(Long senderId, Long receiverId, String message) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.message = message;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
