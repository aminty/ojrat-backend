package com.amin.ojrat.dto.payamak.status;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

public class GetSmsStatusResult {

    private List<String> results;

    private List<Long> resultsAsCode;

    private String status;

    public GetSmsStatusResult() {
    }

    public GetSmsStatusResult(List<String> results, List<Long> resultsAsCode, String status) {
        this.results = results;
        this.resultsAsCode = resultsAsCode;
        this.status = status;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }

    public List<Long> getResultsAsCode() {
        return resultsAsCode;
    }

    public void setResultsAsCode(List<Long> resultsAsCode) {
        this.resultsAsCode = resultsAsCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
