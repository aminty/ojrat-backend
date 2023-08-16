package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.admin.request.AdminCreationDto;
import com.amin.ojrat.dto.entity.expert.request.ExpertCreationDto;
import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.entity.Product;
import com.amin.ojrat.exception.DuringSaveException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpertService {


    void saveExpert(ExpertCreationDto param) throws DuringSaveException;

    Expert findExpertById(Long expertId);

    List<Product> getAllProductFromBranch(Long branchId);

    boolean isExistsExpertByValue(ExpertCreationDto param);

}
