package com.amin.ojrat.dto.entity.message;

import com.amin.ojrat.dto.entity.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class MessageDto {


    private String text;

    private boolean isRead;


    private UserDto sender;


    private UserDto receiver;

    public MessageDto() {
    }

    public MessageDto(String text, boolean isRead, UserDto sender, UserDto receiver) {
        this.text = text;
        this.isRead = isRead;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public UserDto getSender() {
        return sender;
    }

    public void setSender(UserDto sender) {
        this.sender = sender;
    }

    public UserDto getReceiver() {
        return receiver;
    }

    public void setReceiver(UserDto receiver) {
        this.receiver = receiver;
    }
}
