package com.amin.ojrat.service.impl;

import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.entity.Product;
import com.amin.ojrat.repository.ExpertRepository;
import com.amin.ojrat.service.ExpertService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    ExpertRepository expertRepository;

    public void saveExpert(Expert expert){
        expertRepository.save(expert);
    }

    @Override
    public Expert findExpertById(Long expertId) {
        return null;
    }

    @Override
    public List<Product> getAllProductFromBranch(Long branchId) {
        return null;
    }


    public Expert findExpert(Long id){
        return  expertRepository.findById(id).get();
    }
}
