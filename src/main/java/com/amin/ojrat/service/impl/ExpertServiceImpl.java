package com.amin.ojrat.service.impl;

import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.repository.ExpertRepository;
import com.amin.ojrat.service.ExpertService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExpertServiceImpl implements ExpertService {

    ExpertRepository expertRepository;

    public void saveExpert(Expert expert){
        expertRepository.save(expert);
    }


    public Expert findExpert(Long id){
        return  expertRepository.findById(id).get();
    }
}
