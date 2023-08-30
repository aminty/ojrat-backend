package com.amin.ojrat.repository;

import com.amin.ojrat.entity.Branch;
import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.entity.Ticket;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpertRepository extends JpaRepository<Expert,Long> {

    @EntityGraph(attributePaths = { "branches" })
    @Query("SELECT e.branches FROM Expert e WHERE e.Id = :expertId")
    Page<Branch> findAvailableBranchForExpertById(Long expertId, Pageable pageable);

    @EntityGraph(attributePaths = { "tickets" })
    @Query("SELECT e.tickets FROM Expert e WHERE e.Id = :expertId")
    Page<Ticket> findAllRelatedTickets(Long expertId, Pageable pageable);


}
