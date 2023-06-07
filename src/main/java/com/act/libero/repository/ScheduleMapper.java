package com.act.libero.repository;

import org.apache.ibatis.annotations.Mapper;

import com.act.libero.entity.Schedule;

@Mapper
public interface ScheduleMapper {
    Schedule selectSchedule(int scheduleId);

    Schedule chkScheduleExist(int scheduleId);

    Schedule deleteSchedule(int scheduleId,Date updatedAt);
}
