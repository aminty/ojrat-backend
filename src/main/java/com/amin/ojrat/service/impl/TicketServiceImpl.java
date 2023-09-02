package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.ticket.request.SendNewMessageDtoParam;
import com.amin.ojrat.dto.entity.ticket.request.TicketCreationDtoParam;
import com.amin.ojrat.dto.entity.ticket.response.MessageDtoResult;
import com.amin.ojrat.dto.entity.ticket.response.SimpleTicketDtoResult;
import com.amin.ojrat.dto.mapper.TicketMapper;
import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.entity.Message;
import com.amin.ojrat.entity.Ticket;
import com.amin.ojrat.entity.User;
import com.amin.ojrat.enums.TicketStatus;
import com.amin.ojrat.exception.CreationException;
import com.amin.ojrat.exception.PermissionDeniedException;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private ServiceRegistry serviceRegistry;
    private final DaoRepositories daoRepositories;
    private final TicketMapper ticketMapper;


    @Autowired
    public TicketServiceImpl(ServiceRegistry serviceRegistry, TicketMapper ticketMapper,
                             DaoRepositories daoRepositories) {
        this.serviceRegistry = serviceRegistry;
        this.ticketMapper = ticketMapper;
        this.daoRepositories = daoRepositories;
    }


    @Override
    public SimpleTicketDtoResult createTicket(TicketCreationDtoParam param) throws CreationException {
        if (serviceRegistry.getSubjectService().isExistsById(param.getSubjectId())) {
            Ticket ticket = ticketMapper.creationTicketDtoToTicket(param);
            isExpertExistsOrThrow(param.getExpertId());
            isBranchExistsOrThrow(param.getBranchId());
            if (!isExistsTicketByThisSubjectWithStatus(ticket, TicketStatus.OPEN)
                    && !isExistsTicketByThisSubjectWithStatus(ticket, TicketStatus.IN_PROGRESS)) {
                Ticket savedTicket = daoRepositories.getTicketRepository().save(ticket);
                return ticketMapper.TicketToSimpleTicketDto(savedTicket);
            } else
                throw new CreationException("a ticket is exists by this subject and open or in progress status");
        }
        throw new CreationException("can not create ticket!");
    }
    @Transactional
    @Override
    public void sendMessageInsideTicket(SendNewMessageDtoParam param) {
        Ticket ticket =findTicketById(param.getTicketId());
        User sender=serviceRegistry.getUserService().findUserById(param.getSenderId());
        Message message=new Message();
        message.setSender(sender);
        message.setText(param.getMessage());
        ticket.getMessages().add(message);
        if (sender instanceof Expert){
            ticket.setExistsNewMessageForBranch(true);
            ticket.setExistsNewMessageForExpert(false);
        }else {
            ticket.setExistsNewMessageForBranch(false);
            ticket.setExistsNewMessageForExpert(true);
        }
        saveOrUpdateTicket(ticket);
    }

    @Transactional
    @Override
    public List<MessageDtoResult> getAllMessageOfTicket(Long ticketId) {
        setReadTicket(ticketId);
        Optional<Ticket> foundTicket = daoRepositories.getTicketRepository().findById(ticketId);
        if (foundTicket.isPresent()) {
            List<Message> messages = foundTicket.get().getMessages();
            return messages.stream().map(ticketMapper::messageToMessageDto).collect(Collectors.toList());
        } else return Collections.emptyList();
    }

    private void setReadTicket(Long ticketId) {
        Ticket ticketById = findTicketById(ticketId);
        if (ticketById.isExistsNewMessageForBranch())
            ticketById.setExistsNewMessageForBranch(false);
        if (ticketById.isExistsNewMessageForExpert())
            ticketById.setExistsNewMessageForExpert(false);
        saveOrUpdateTicket(ticketById);
    }

    @Override
    public void saveOrUpdateTicket(Ticket ticket) {
        daoRepositories.getTicketRepository().save(ticket);
    }

    private void isExpertExistsOrThrow(Long expertId) {
        boolean existsById = serviceRegistry.getExpertService().isExistsById(expertId);
        if (!existsById)
            throw new EntityNotFoundException("expert not found");
    }


    private void isBranchExistsOrThrow(Long branchId) {
        boolean existsById = serviceRegistry.getBranchService().isExistsById(branchId);
        if (!existsById)
            throw new EntityNotFoundException("branch not found");
    }

    private boolean isExistsTicketByThisSubjectWithStatus(Ticket ticket, TicketStatus status) {
        return daoRepositories
                .getTicketRepository()
                .existsTicketByStatusAndBranch_IdAndExpert_IdAndSubject_Id(
                        status,
                        ticket.getBranch().getId(),
                        ticket.getExpert().getId(),
                        ticket.getSubject().getId());
    }

    @Override
    public void closeTicketByExpert(Long ticketId, Long expertId) throws PermissionDeniedException {
        Ticket ticketById = findTicketById(ticketId);
        if (Objects.equals(ticketById.getExpert().getId(), expertId)) {
            ticketById.setStatus(TicketStatus.CLOSED);
            daoRepositories.getTicketRepository().save(ticketById);
        } else throw new PermissionDeniedException("you have no permission to close ticket");
    }

    @Override
    public Ticket findTicketById(Long ticketId) {
        return daoRepositories
                .getTicketRepository()
                .findById(ticketId)
                .orElseThrow(() -> new EntityNotFoundException("ticket not found"));
    }

    @Override
    @Transactional
    public List<SimpleTicketDtoResult> getRelatedTicket(Long branchId, Long ticketId) {
        List<Ticket> foundTickets = daoRepositories
                .getTicketRepository()
                .findAllByBranch_IdAndExpert_Id(branchId, ticketId);
        if (!foundTickets.isEmpty())
            return foundTickets.stream().map(ticketMapper::TicketToSimpleTicketDto).collect(Collectors.toList());
        else return Collections.emptyList();
    }
}
