package com.act.libero.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ScheduleEditInfo implements Serializable {

    private int scheduleId;
    private int groupId;
    private String titleName;
    private Date fromDateTime;
    private Date toDateTime;
    private String address;
    private String memo;
    private int announceFlag;
    private String calendarType;
}
