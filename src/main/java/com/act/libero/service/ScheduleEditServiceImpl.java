package com.act.libero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.act.libero.dto.ScheduleEditInfo;
import com.act.libero.entity.ScheduleEdit;
import com.act.libero.repository.ScheduleEditMapper;

@Service
public class ScheduleEditServiceImpl implements ScheduleEditService {

    /**
     * スケジュール情報情報 Mapper
     */
    @Autowired
    private ScheduleEditMapper scheduleEditMapper;

    public ScheduleEditInfo init(Integer scheduleId) {
        ScheduleEditInfo result = new ScheduleEditInfo();
        if (scheduleId != null) {
            ScheduleEdit items = scheduleEditMapper.getScheduleInfo(scheduleId);
            result = createInitItem(items);
        }
        return result;
    }

    /**
     * 初期表示画面項目作成用
     */
    private ScheduleEditInfo createInitItem(ScheduleEdit se) {
        ScheduleEditInfo items = new ScheduleEditInfo();
        items.setScheduleId(se.getScheduleId());
        items.setGroupId(se.getGroupId());
        items.setTitleName(se.getTitleName());
        items.setFromDateTime(se.getFromDateTime());
        items.setToDateTime(se.getToDateTime());
        items.setAddress(se.getAddress());
        items.setMemo(se.getMemo());
        items.setAnnounceFlag(se.getAnnounceFlag());
        return items;
    }
}
