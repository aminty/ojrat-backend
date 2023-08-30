package com.amin.ojrat.dto.entity.ticket.request;

import jakarta.validation.constraints.NotNull;

public class ChangeTicketStatusDtoParam {


    @NotNull(message = "ticket id should not be null")
    private Long ticketId;


    @NotNull(message = "expert id should not be null")
    private Long expertId;

    public ChangeTicketStatusDtoParam() {
    }

    public ChangeTicketStatusDtoParam(Long ticketId, Long expertId) {
        this.ticketId = ticketId;
        this.expertId = expertId;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }
}
