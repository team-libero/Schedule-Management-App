package com.act.libero.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import com.act.libero.dto.ScheduleInfo;
import com.act.libero.dto.ScheduleInfoList;
import com.act.libero.dto.SessionInfo;
import com.act.libero.service.CalendarDisplayService;
import io.micrometer.common.util.StringUtils;

@Controller
public class CalendarDisplayController {

	@Autowired
	CalendarDisplayService calendarDisplayService;

	@Autowired
	ScheduleInfo scheduleInfo;

	@Autowired
	SessionInfo sessionInfo;

	/*
	 * 初期表示時、カレンダー切替時
	 * スケジュール情報:DBから取得後セッションに格納（月単位）
	 */
	@GetMapping(value = "/calendarDisplay")
	public String calendarDisplayInit(@ModelAttribute ScheduleInfo scheduleInfo, Model model,
			@RequestParam(required = false) String targetDate) {
		// String selectDate, HttpSession sessionInfo) {

		// ↓↓↓↓↓↓↓↓セッションからユーザー情報を取得
		// String userId = (String) sessionInfo.getAttribute("userId");
		// String usersGroupId = (String) sessionInfo.getAttribute("usersGroupId");
		String userId = "1";
		Integer groupId = null;

		String searchDate = "";
		if (StringUtils.isEmpty(targetDate)) {
			// 今日日付をセット
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date currentDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			searchDate = sdf.format(currentDate);
		} else {
			searchDate = targetDate;
		}

		// DBから現在月の対象スケジュール取得
		List<ScheduleInfoList> scheduleInfoList = new ArrayList<ScheduleInfoList>();
		scheduleInfoList = calendarDisplayService.selectTargetSchedule(userId, groupId, searchDate);

		// 現在月のスケジュール情報をセッションに設定
		sessionInfo.setScheduleInfoList(scheduleInfoList);

		return "calendarDisplay";
	}

	/*
	 * 日時選択時
	 * スケジュール情報:DBから取得後セッションに格納（月単位）
	 */
	@GetMapping(value = "/calendarDisplay")
	public String calendarDisplaySelectDay(@ModelAttribute ScheduleInfo scheduleInfo, Model model,
			@RequestParam(required = false) String targetDate) {
		// String selectDate, HttpSession sessionInfo) {

		// ↓↓↓↓↓↓↓↓セッションからユーザー情報、スケジュール情報を取得
		// String userId = (String) sessionInfo.getAttribute("userId");
		// String usersGroupId = (String) sessionInfo.getAttribute("usersGroupId");
		List<ScheduleInfoList> scheduleInfoList = sessionInfo.getScheduleInfoList();

		// String userId = "1";
		// Integer groupId = null;

		// DB検索用日付
		// String searchDate = targetDate;

		// DBから対象スケジュール取得
		// List<ScheduleInfoList> scheduleInfoList = new ArrayList<ScheduleInfoList>();
		// scheduleInfoList = calendarDisplayService.selectTargetSchedule(userId,
		// groupId, searchDate);

		// 対象スケジュール情報をDTOにセット
		scheduleInfo.setScheduleList(scheduleInfoList);

		// 選択日をセット
		scheduleInfo.setTargetDate(targetDate);

		model.addAttribute("scheduleInfo", scheduleInfo);

		return "calendarDisplay";
	}

}