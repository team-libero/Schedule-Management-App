package com.act.libero.service;

import com.act.libero.dto.ScheduleEditInfo;

public interface ScheduleEditService {

    // スケジュール情報取得
    ScheduleEditInfo init(Integer scheduleId);
    
}
