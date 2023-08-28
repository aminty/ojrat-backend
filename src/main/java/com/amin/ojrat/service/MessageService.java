package com.amin.ojrat.service;

public interface MessageService {

    void sendMessage(Long senderId,Long receiverId,String msg);

    void updateMessage(Long messageId,Long senderId,String msg);

    void deleteMessage(Long messageId,Long senderId,String msg);

    void setReadMessage(Long messageId);
}
