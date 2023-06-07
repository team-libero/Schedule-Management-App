package com.act.libero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.act.libero.entity.Schedule;
import com.act.libero.repository.ScheduleMapper;

@Service
public class ScheduleDetailService {
    
    /**
     * スケジュール情報情報 Mapper
     */
    @Autowired
    private ScheduleMapper scheduleMapper;

    /**
     * スケジュール情報取得
     * return 検索結果
     */
    public Schedule selectSchedule(int scheduleId) {
        return scheduleMapper.selectSchedule(scheduleId);
    }

    /**
     * スケジュール情報取得
     * return 検索結果
     */
    public Schedule chkScheduleExist(int scheduleId) {
        return scheduleMapper.chkScheduleExist(scheduleId);
    }

    /**
     * スケジュール情報取得
     * return 検索結果
     */
    public Schedule deleteSchedule(int scheduleId) {
        return scheduleMapper.deleteSchedule(scheduleId);
    }
}
