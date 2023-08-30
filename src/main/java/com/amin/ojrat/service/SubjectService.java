package com.amin.ojrat.service;

import com.amin.ojrat.entity.Service;
import com.amin.ojrat.entity.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> findAllSubject();

    boolean isExistsById(Long subjectId);
}
