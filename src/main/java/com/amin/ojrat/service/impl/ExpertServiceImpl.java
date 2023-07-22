package com.amin.ojrat.service.impl;

import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.entity.Product;
import com.amin.ojrat.repository.IExpertRepository;
import com.amin.ojrat.service.ExpertService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    IExpertRepository IExpertRepository;

    public void saveExpert(Expert expert){
        IExpertRepository.save(expert);
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
        return  IExpertRepository.findById(id).get();
    }
}
