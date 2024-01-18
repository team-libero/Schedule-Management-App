package com.act.libero.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.act.libero.dto.ScheduleInfoList;

@Mapper
public interface CalendarDisplayMapper {
    List <ScheduleInfoList> selectTargetSchedule(String userId, String targetMonthStr);

    List <ScheduleInfoList> selectTargetScheduleGrp(Integer groupId, String targetMonthStr);
}
