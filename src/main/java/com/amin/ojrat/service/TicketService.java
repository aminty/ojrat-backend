package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.ticket.request.SendNewMessageDtoParam;
import com.amin.ojrat.dto.entity.ticket.request.TicketCreationDtoParam;
import com.amin.ojrat.dto.entity.ticket.response.MessageDtoResult;
import com.amin.ojrat.dto.entity.ticket.response.SimpleTicketDtoResult;
import com.amin.ojrat.entity.Ticket;
import com.amin.ojrat.exception.CreationException;
import com.amin.ojrat.exception.DeletionException;
import com.amin.ojrat.exception.PermissionDeniedException;

import java.util.List;

public interface TicketService {

    SimpleTicketDtoResult createTicket(TicketCreationDtoParam param) throws CreationException;

    void sendMessageInsideTicket(SendNewMessageDtoParam param);

    List<MessageDtoResult> getAllMessageOfTicket(Long ticketId);

    void saveOrUpdateTicket(Ticket ticket);

    void closeTicketByExpert(Long ticketId,Long expertId) throws PermissionDeniedException;

    Ticket findTicketById(Long ticketId);

    List<SimpleTicketDtoResult> getRelatedTicket(Long branchId,Long ticketId);


}
