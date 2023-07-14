package com.amin.ojrat.service;

import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpertService {


    void saveExpert(Expert expert);

    Expert findExpertById(Long expertId);

    List<Product> getAllProductFromBranch(Long branchId);


}
