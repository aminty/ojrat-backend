package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.ticket.request.SendNewMessageDtoParam;
import com.amin.ojrat.dto.entity.ticket.request.TicketCreationDtoParam;
import com.amin.ojrat.dto.entity.ticket.response.MessageDtoResult;
import com.amin.ojrat.dto.entity.ticket.response.SimpleTicketDtoResult;
import com.amin.ojrat.dto.entity.ticket.response.SubjectDtoResult;
import com.amin.ojrat.entity.Message;
import com.amin.ojrat.entity.Subject;
import com.amin.ojrat.entity.Ticket;
import com.amin.ojrat.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {

        @Mapping(source = "branchId", target = "branch.id")
        @Mapping(source = "expertId", target = "expert.id")
        @Mapping(source = "subjectId",target = "subject.id")
        @Mapping(target = "messages", expression = "java(createMessage(param.getMessage(), param.getSenderId()))")
        Ticket creationTicketDtoToTicket(TicketCreationDtoParam param);

        default List<Message> createMessage(String message, Long senderId) {
            Message newMessage = new Message();
            newMessage.setText(message);
            User sender = new User();
            sender.setId(senderId);
            newMessage.setSender(sender);
            newMessage.setSentAt(new Timestamp(System.currentTimeMillis())); // Set creation timestamp
            newMessage.setUpdatedAt(new Timestamp(System.currentTimeMillis())); // Set update timestamp
            return Collections.singletonList(newMessage);
        }

        SubjectDtoResult subjectToSubjectDto(Subject subject);


//        @Mapping(source ="ticketId",target = "id")
//        @Mapping(target = "messages", expression = "java(createMessage(param.getMessage(), param.getSenderId()))")
//        Ticket sendNewMessageDto(SendNewMessageDtoParam param);


        @Mapping(source = "sender.id",target = "sender.id")
        @Mapping(source = "sender.firstName",target = "sender.firstName")
        @Mapping(source = "sender.lastName",target = "sender.lastName")
        @Mapping(source = "replyTo.id",target = "replyTo")
        MessageDtoResult messageToMessageDto(Message param);

        @Mapping(source = "ticket.id",target = "id")
        @Mapping(source = "branch.name",target = "branchName")
        @Mapping(source = "subject.title",target = "subject")
        SimpleTicketDtoResult TicketToSimpleTicketDto(Ticket ticket);




}
