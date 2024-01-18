package com.act.libero.service;

import java.util.Date;

import com.act.libero.dto.ScheduleEditInfo;
import com.act.libero.entity.ScheduleEdit;

import jakarta.servlet.http.HttpSession;

public interface ScheduleEditService {

    // 初期表示_編集
    ScheduleEditInfo initEdit(Integer scheduleId);
    
    // 初期表示_登録
    ScheduleEditInfo initRegist(String date, String calendarType);

    // 登録・編集
    String register(ScheduleEdit scheduleEdit, HttpSession session);
}
