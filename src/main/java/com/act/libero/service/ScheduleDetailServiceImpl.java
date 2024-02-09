package com.act.libero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.act.libero.dto.ScheduleInfo;
import com.act.libero.entity.Schedule;
import com.act.libero.repository.ScheduleMapper;

import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class ScheduleDetailServiceImpl implements ScheduleDetailService{

    /**
     * スケジュール情報情報 Mapper
     */
    @Autowired
    private ScheduleMapper scheduleMapper;

    /**
     * スケジュール情報取得
     * return 検索結果
     */
    @Override
    public ScheduleInfo selectSchedule(int scheduleId, HttpSession session) {

        Schedule schedule = scheduleMapper.selectSchedule(scheduleId);
        if (schedule == null) {
            return null;
        }
        // 項目編集
        ScheduleInfo scheduleTmp = editItem(schedule);
        session.setAttribute("updDate",scheduleTmp.getUpdatedAt());
        scheduleTmp.setScheduleId(scheduleId);

        return scheduleTmp;
    }

    /**
     * スケジュール存在チェック
     * @param HttpSession session
     * @param RedirectAttributes redirectAttributes
     * @return true/falses
     */
    @Override
    public boolean chkScheduleExist(int scheduleId, HttpSession session) {
        Date updDate = (Date)session.getAttribute("updDate");
        Integer scheduleExistChk = scheduleMapper.chkScheduleExist(scheduleId, updDate);
        if (scheduleExistChk != 1) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * スケジュール削除
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSchedule(int scheduleId, HttpSession session) {
        Date updDate = (Date)session.getAttribute("updDate");
        scheduleMapper.deleteSchedule(scheduleId, updDate);
    }

    /**
     * 項目編集
     * 
     * @param Schedule schedule
     * @return editData
     */
    private ScheduleInfo editItem(Schedule schedule) {
        ScheduleInfo editData = new ScheduleInfo();

        // 項目定義
        String fromDateStr = null;
        String fromTimeStr = null;
        String dayOfweekFromStr = null;
        String toDateStr = null;
        String toTimeStr = null;
        String dayOfweekToStr = null;
        String scheduleKbn = null;

        // 変換前データ
        Date fromDate = schedule.getFromDateTime();
        Date toDate = schedule.getToDateTime();

        // 変換処理
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM月　dd日　");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH：mm");

        // 日付
        fromDateStr = sdfDate.format(fromDate);
        toDateStr = sdfDate.format(toDate);
        // 時間
        fromTimeStr = sdfTime.format(fromDate);
        toTimeStr = sdfTime.format(toDate);
        // 曜日
        dayOfweekFromStr = getDayOfWeek(fromDate);
        dayOfweekToStr = getDayOfWeek(toDate);
        // スケジュール区分
        if (schedule.getUsersGroupId() == null) {
            scheduleKbn = "個人スケジュール";
        } else {
            scheduleKbn = "グループスケジュール";
        }

        editData.setTitleName(schedule.getTitleName());
        editData.setAddress(schedule.getAddress());
        editData.setMemo(schedule.getMemo());
        editData.setGroupId(schedule.getUsersGroupId());
        editData.setLastName(schedule.getLastName());
        editData.setFirstName(schedule.getFirstName());
        editData.setUserId(schedule.getUserId());
        editData.setUpdatedAt(schedule.getUpdatedAt());
        editData.setFromDate(fromDateStr);
        editData.setToDate(toDateStr);
        editData.setFromTime(fromTimeStr);
        editData.setToTime(toTimeStr);
        editData.setDayOfWeekFrom(dayOfweekFromStr);
        editData.setDayOfWeekTo(dayOfweekToStr);
        editData.setScheduleKbn(scheduleKbn);

        return editData;
    }

    /**
     * 曜日取得処理
     * 
     * @param Date Date
     * @return result
     */
    private String getDayOfWeek(Date date) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        String result = null;
        switch (cl.get(Calendar.DAY_OF_WEEK)) {
            case (Calendar.MONDAY):
                result = "（月）";
                break;
            case (Calendar.TUESDAY):
                result = "（火）";
                break;
            case (Calendar.WEDNESDAY):
                result = "（水）";
                break;
            case (Calendar.THURSDAY):
                result = "（木）";
                break;
            case (Calendar.FRIDAY):
                result = "（金）";
                break;
            case (Calendar.SATURDAY):
                result = "（土）";
                break;
            case (Calendar.SUNDAY):
                result = "（日）";
                break;
            default:
                throw new IllegalStateException();
        }
        return result;
    }
}
