package com.amin.ojrat.repository;

import com.amin.ojrat.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order , Long> {
}
