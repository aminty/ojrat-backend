package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.ticket.request.TicketCreationDtoParam;
import com.amin.ojrat.dto.mapper.TicketMapper;
import com.amin.ojrat.entity.Ticket;
import com.amin.ojrat.enums.TicketStatus;
import com.amin.ojrat.exception.CreationException;
import com.amin.ojrat.exception.DeletionException;
import com.amin.ojrat.exception.PermissionDeniedException;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
    public void createTicket(TicketCreationDtoParam param) throws CreationException {

        if (serviceRegistry.getSubjectService().isExistsById(param.getSubjectId())) {
            Ticket ticket = ticketMapper.creationTicketDtoToTicket(param);
            isExpertExistsOrThrow(param.getExpertId());
            isBranchExistsOrThrow(param.getBranchId());
            if (!isExistsTicketByThisSubjectWithStatus(ticket,TicketStatus.OPEN)
                    && !isExistsTicketByThisSubjectWithStatus(ticket,TicketStatus.IN_PROGRESS)){
                daoRepositories.getTicketRepository().save(ticket);
            }else
                throw new CreationException("a ticket is exists by this subject and open or in progress status");
        }
    }

   private void isExpertExistsOrThrow(Long expertId){
       boolean existsById = serviceRegistry.getExpertService().isExistsById(expertId);
       if (!existsById)
           throw new EntityNotFoundException("expert not found");
   }


   private void isBranchExistsOrThrow(Long branchId){
       boolean existsById = serviceRegistry.getBranchService().isExistsById(branchId);
       if (!existsById)
           throw new EntityNotFoundException("branch not found");
   }

    private boolean isExistsTicketByThisSubjectWithStatus(Ticket ticket,TicketStatus status) {
        return daoRepositories
                .getTicketRepository()
                .existsTicketByStatusAndBranch_IdAndExpert_IdAndSubject_Id(
                        status,
                        ticket.getBranch().getId(),
                        ticket.getExpert().getId(),
                        ticket.getSubject().getId());
    }


    @Override
    public void updateMessage(Long messageId, Long senderId, String msg) {

    }

    @Override
    public void deleteMessageForSender(Long messageId) throws DeletionException {

    }

    @Override
    public void deleteMessageForReceiver(Long messageId) throws DeletionException {

    }

    @Override
    public void deleteMessage(Long messageId, Long senderId, String msg) {

    }

    @Override
    public void setReadMessage(Long messageId) {

    }

    @Override
    public void setReadMessage(Long messageId, Long senderId, Long receiverId) {

    }

    @Override
    public void closeTicketByExpert(Long ticketId,Long expertId) throws PermissionDeniedException {
        Ticket ticketById = findTicketById(ticketId);
        if (Objects.equals(ticketById.getExpert().getId(), expertId)) {
            ticketById.setStatus(TicketStatus.CLOSED);
            daoRepositories.getTicketRepository().save(ticketById);
        }else throw new PermissionDeniedException("you have no permission to close ticket");
    }

    @Override
    public Ticket findTicketById(Long ticketId){
      return  daoRepositories
                .getTicketRepository()
                .findById(ticketId)
                .orElseThrow(()->new EntityNotFoundException("ticket not found"));

    }
}
