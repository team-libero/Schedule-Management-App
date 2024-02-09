package com.act.libero.service;

import com.act.libero.dto.ScheduleInfo;

import jakarta.servlet.http.HttpSession;

public interface ScheduleDetailService {

    // スケジュール情報取得
    ScheduleInfo selectSchedule(int scheduleId, HttpSession session);

    // スケジュール存在チェック
    boolean chkScheduleExist(int scheduleId, HttpSession session);

    // スケジュール削除
    void deleteSchedule(int scheduleId, HttpSession session);
    
}
