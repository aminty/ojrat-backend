package com.amin.ojrat.dto.mapper;

import com.amin.ojrat.dto.entity.ticket.request.TicketCreationDtoParam;
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
            newMessage.setCreatedAt(new Timestamp(System.currentTimeMillis())); // Set creation timestamp
            newMessage.setUpdatedAt(new Timestamp(System.currentTimeMillis())); // Set update timestamp
            newMessage.setRead(false); // Assuming it's initially unread
            return Collections.singletonList(newMessage);
        }


        SubjectDtoResult subjectToSubjectDtoResult(Subject subject);

}
