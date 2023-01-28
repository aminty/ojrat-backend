package com.amin.ojrat.repository;

import com.amin.ojrat.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order , Long> {
}
