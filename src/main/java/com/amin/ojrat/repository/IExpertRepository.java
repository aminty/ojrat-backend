package com.amin.ojrat.repository;

import com.amin.ojrat.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExpertRepository extends JpaRepository<Expert,Long> {
}
