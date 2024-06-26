package com.act.libero.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ScheduleMonthInfo implements Serializable {

  private List<ScheduleInfoList> scheduleList;

  /** 選択日 */
  private String selectDate;

}