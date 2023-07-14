package com.amin.ojrat.repository;

import com.amin.ojrat.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code,Long> {

    Code findByPhoneNumber(String phoneNumber);

    void deleteByPhoneNumber(String phoneNumber);
}
