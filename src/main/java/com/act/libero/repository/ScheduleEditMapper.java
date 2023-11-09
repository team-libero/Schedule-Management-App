package com.act.libero.repository;

import org.apache.ibatis.annotations.Mapper;

import com.act.libero.entity.ScheduleEdit;

@Mapper
public interface ScheduleEditMapper {
    ScheduleEdit getScheduleInfo (int scheduleId);
}
