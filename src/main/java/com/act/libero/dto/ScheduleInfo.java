package com.act.libero.dto;

import java.io.Serializable;
import lombok.Data;

import java.util.List;
import org.springframework.stereotype.Component;

@Data
@Component
public class ScheduleInfo implements Serializable {

  private List<ScheduleInfoList> scheduleList;

  /** 選択日 */
  private String selectDate;
}