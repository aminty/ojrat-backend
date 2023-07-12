package com.amin.ojrat.repository;

import com.amin.ojrat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
