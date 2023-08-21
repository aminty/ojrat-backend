package com.amin.ojrat.dto.payamak.status;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

public class GetSmsStatusResult {

    private List<String> results;

    private List<Integer> resultsAsCode;

    private String status;

    public GetSmsStatusResult() {
    }

    public GetSmsStatusResult(List<String> results, List<Integer> resultsAsCode, String status) {
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

    public List<Integer> getResultsAsCode() {
        return resultsAsCode;
    }

    public void setResultsAsCode(List<Integer> resultsAsCode) {
        this.resultsAsCode = resultsAsCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
