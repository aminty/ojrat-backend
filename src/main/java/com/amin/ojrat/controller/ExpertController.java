package com.amin.ojrat.controller;

import com.amin.ojrat.dto.entity.expert.request.ExpertCreationDto;
import com.amin.ojrat.exception.CreationException;
import com.amin.ojrat.exception.UserExistsException;
import com.amin.ojrat.service.ServiceRegistry;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expert")
public class ExpertController {

    private final ServiceRegistry serviceRegistry;

    public ExpertController(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    @PostMapping("/create-expert")
    public ResponseEntity<String> createNewExpertWithValidation(@Valid @RequestBody ExpertCreationDto param) throws Exception  {
        if (param==null)
            throw new CreationException("server receive null object!");
        if (!serviceRegistry.getExpertService().isExistsExpertByValue(param)) {
            serviceRegistry.getExpertService().saveExpert(param);
            return new ResponseEntity<>("expert created successfully.", HttpStatus.CREATED);
        }
        else {
            throw new UserExistsException("one of this params is taken by another user!");
        }
    }


}
