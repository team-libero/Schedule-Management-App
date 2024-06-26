package com.act.libero.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.act.libero.dto.ScheduleInfoList;
import com.act.libero.dto.ScheduleMonthInfo;
import com.act.libero.dto.SessionInfo;
import com.act.libero.service.CalendarDisplayService;
import io.micrometer.common.util.StringUtils;

@Controller
public class CalendarDisplayController {

	@Autowired
	CalendarDisplayService calendarDisplayService;

	@Autowired
	SessionInfo sessionInfo;

	/*
	 * カレンダー表示画面
	 */
	@RequestMapping(value = "/calendarDisplay")
	public String calendarDisplayInit(Model model,
			@RequestParam(required = false) String selectDate, @RequestParam(required = false) String switchCalendar) {
		// String selectDate, HttpSession sessionInfo) {

		// ↓↓↓↓↓↓↓↓セッションからユーザー情報を取得
		// String userId = (String) sessionInfo.getAttribute("userId");
		// String usersGroupId = (String) sessionInfo.getAttribute("usersGroupId");
		String userId = "1";
		Integer groupId = null;

		// スケジュール情報格納リスト
		ScheduleMonthInfo scheduleMonthInfo = new ScheduleMonthInfo();
		List<ScheduleInfoList> scheduleInfoList = new ArrayList<ScheduleInfoList>();

		/*
		 * 初期表示
		 */
		if (StringUtils.isEmpty(selectDate) && StringUtils.isEmpty(switchCalendar)) {

			// 現在日時取得
			String searchDate = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date currentDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			searchDate = sdf.format(currentDate);

			// DBから現在月の対象スケジュール取得
			scheduleInfoList = calendarDisplayService.selectTargetSchedule(userId, groupId, searchDate);

			// スケジュール情報をセッションに設定
			sessionInfo.setScheduleInfoList(scheduleInfoList);

			/*
			 * 日付選択
			 */
		} else if (StringUtils.isNotEmpty(selectDate) && StringUtils.isEmpty(switchCalendar)) {
			/* 日付選択 */
			// セッションから選択月の対象スケジュール取得
			scheduleInfoList = sessionInfo.getScheduleInfoList();

			// スケジュール情報をセッションに設定
			sessionInfo.setScheduleInfoList(scheduleInfoList);

			// 対象スケジュール情報をDTOにセット
			scheduleMonthInfo.setScheduleList(scheduleInfoList);

			scheduleMonthInfo.setSelectDate(selectDate);

			model.addAttribute("scheduleInfo", scheduleMonthInfo);

			/*
			 * カレンダー切替
			 */
		} else if (StringUtils.isEmpty(selectDate) && StringUtils.isNotEmpty(switchCalendar)) {
			// DBから対象月の対象スケジュール取得
			scheduleInfoList = calendarDisplayService.selectTargetSchedule(userId, groupId, switchCalendar);

			// スケジュール情報をセッションに設定
			sessionInfo.setScheduleInfoList(scheduleInfoList);
		}

		return "calendarDisplay";
	}

}