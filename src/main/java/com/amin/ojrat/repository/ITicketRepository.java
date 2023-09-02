package com.amin.ojrat.repository;

import com.amin.ojrat.entity.Ticket;
import com.amin.ojrat.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Long> {

    boolean existsTicketByStatusAndBranch_IdAndExpert_IdAndSubject_Id
            (TicketStatus status,Long branchId,Long expertId,Long subjectId);

    List<Ticket> findAllByBranch_IdAndExpert_Id(Long branchId,Long expertId);


}
