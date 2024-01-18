package com.act.libero.dto;

import java.io.Serializable;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SelectScheduleInfo implements Serializable {

   /** 対象日 */
   private String targetDate;

   /** 選択日 */
   private String selectDate;
}
