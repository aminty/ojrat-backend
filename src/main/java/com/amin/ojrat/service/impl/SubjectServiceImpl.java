package com.amin.ojrat.service.impl;

import com.amin.ojrat.entity.Subject;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.SubjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubjectServiceImpl implements SubjectService {
    private final DaoRepositories daoRepositories;

    public SubjectServiceImpl(DaoRepositories daoRepositories) {
        this.daoRepositories = daoRepositories;
    }

    @Override
    public List<Subject> findAllSubject() {
        return daoRepositories.getSubjectRepository().findAll();
    }

    @Override
    public boolean isExistsById(Long subjectId){
        return daoRepositories
                .getSubjectRepository()
                .existsById(subjectId);
    }
}
