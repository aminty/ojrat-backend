package com.amin.ojrat.dto.payamak.status;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;


public class GetSmsStatusParam {

     private List<Long> recIds=new ArrayList<>();

     public GetSmsStatusParam() {
     }

     public GetSmsStatusParam(List<Long> recIds) {
          this.recIds = recIds;
     }

     public List<Long> getRecIds() {
          return recIds;
     }

     public void setRecIds(List<Long> recIds) {
          this.recIds = recIds;
     }
}
