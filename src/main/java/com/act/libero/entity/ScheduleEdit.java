package com.act.libero.entity;

import java.util.Date;

import lombok.Data;

@Data
public class ScheduleEdit {

    private int scheduleId;
    private int groupId;
    private String userId;
    private String titleName;
    private String fromDateTime;
    private String toDateTime;
    private String address;
    private String memo;
    private int announceFlag;
    private String createdUserId;
    private Date createdAt;
    private String updatedUserId;
    private Date updatedAt;
    
}
