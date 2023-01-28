package com.amin.ojrat.repository;

import com.amin.ojrat.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {


}
