package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.ticket.request.TicketCreationDtoParam;
import com.amin.ojrat.entity.Ticket;
import com.amin.ojrat.exception.CreationException;
import com.amin.ojrat.exception.DeletionException;
import com.amin.ojrat.exception.PermissionDeniedException;

public interface TicketService {

    void createTicket(TicketCreationDtoParam param) throws CreationException;

    void updateMessage(Long messageId,Long senderId,String msg);

    void deleteMessageForSender(Long messageId) throws DeletionException;

    void deleteMessageForReceiver(Long messageId) throws DeletionException;

    void deleteMessage(Long messageId, Long senderId, String msg);

    void setReadMessage(Long messageId);

    void setReadMessage(Long messageId, Long senderId, Long receiverId);

     void closeTicketByExpert(Long ticketId,Long expertId) throws PermissionDeniedException;

    Ticket findTicketById(Long ticketId);
}
