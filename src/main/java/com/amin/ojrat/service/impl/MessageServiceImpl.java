package com.amin.ojrat.service.impl;

import com.amin.ojrat.entity.Message;
import com.amin.ojrat.entity.User;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.MessageService;
import com.amin.ojrat.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private final DaoRepositories daoRepositories;
    private final UserService userService;

    public MessageServiceImpl(DaoRepositories daoRepositories, UserService userService) {
        this.daoRepositories = daoRepositories;
        this.userService = userService;
    }


    @Override
    public void sendMessage(Long senderId, Long receiverId, String msg) {
        Message message=new Message();
        User sender = userService.findUserById(senderId);
        User receiver =userService.findUserById(receiverId);
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setText(msg);
        message.setRead(false);
        daoRepositories.getMessageRepository().save(message);

    }

    @Override
    public void updateMessage(Long messageId, Long senderId, String msg) {

    }

    @Override
    public void deleteMessage(Long messageId, Long senderId, String msg) {

    }

    @Override
    public void setReadMessage(Long messageId){

    }
}
