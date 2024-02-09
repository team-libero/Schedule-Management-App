package com.act.libero.service;

import java.util.Date;

import com.act.libero.dto.ScheduleInfo;

import jakarta.servlet.http.HttpSession;

public interface ScheduleDetailService {

    // スケジュール情報取得
    ScheduleInfo selectSchedule(int scheduleId, HttpSession session);

    // スケジュール存在チェック
    boolean chkScheduleExist(int scheduleId, Date updDate);

    // スケジュール削除
    void deleteSchedule(int scheduleId, Date updDate);
    
}
