package com.act.libero.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 初期表示_編集
     */
    public ScheduleEditInfo initEdit(Integer scheduleId) {
        ScheduleEditInfo result = new ScheduleEditInfo();
        if (scheduleId != null) {
            ScheduleEdit items = scheduleEditMapper.getScheduleInfo(scheduleId);
            result = createInitItem(items);
        }
        return result;
    }

    /**
     * 初期表示_登録
     */
    public ScheduleEditInfo initRegist(String date, String calendarType) {
        ScheduleEditInfo result = new ScheduleEditInfo();
        String dateStr = date;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dates = new Date();
        try {
            dates = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result.setFromDateTime(dates);
        result.setToDateTime(dates);
        result.setCalendarType(calendarType);
        return result;
    }

    /**
     * 初期表示_編集 画面項目作成用
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
