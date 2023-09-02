package com.amin.ojrat.dto.entity.ticket.response;

import com.amin.ojrat.dto.entity.base.Context;
import com.amin.ojrat.dto.entity.user.response.SimpleUserDtoResult;

import java.sql.Timestamp;

public class MessageDtoResult extends Context {

    private Long replyTo;

    private String text;

    private SimpleUserDtoResult sender;

    private Timestamp sentAt;

    private Timestamp updatedAt;

    public Long getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Long replyTo) {
        this.replyTo = replyTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SimpleUserDtoResult getSender() {
        return sender;
    }

    public void setSender(SimpleUserDtoResult sender) {
        this.sender = sender;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }




}
