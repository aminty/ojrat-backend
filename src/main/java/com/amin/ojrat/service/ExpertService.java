package com.amin.ojrat.service;

import com.amin.ojrat.dto.entity.ExBrReq.request.ExpBrParam;
import com.amin.ojrat.dto.entity.ExBrReq.response.ExpBrBasicResult;
import com.amin.ojrat.dto.entity.admin.request.AdminCreationDto;
import com.amin.ojrat.dto.entity.expert.request.ExpertCreationDto;
import com.amin.ojrat.entity.Expert;
import com.amin.ojrat.entity.Product;
import com.amin.ojrat.exception.DuringSaveException;
import com.amin.ojrat.exception.NotFullyRegisteredException;
import com.amin.ojrat.exception.RequestLimitExceededException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExpertService {

    void saveExpert(ExpertCreationDto param) throws DuringSaveException;


    boolean isExistsExpertByValue(ExpertCreationDto param);

    void makeJoinRequest(ExpBrParam param) throws NotFullyRegisteredException, RequestLimitExceededException;

    List<ExpBrBasicResult> getAllJoinRequest(Long expertId);


}
