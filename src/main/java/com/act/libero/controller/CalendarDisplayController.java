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
import com.act.libero.dto.ScheduleInfo;
import com.act.libero.dto.ScheduleInfoList;
import com.act.libero.dto.SessionInfo;
import com.act.libero.service.CalendarDisplayService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;

@Controller
public class CalendarDisplayController {

	@Autowired
    CalendarDisplayService calendarDisplayService;

	@Autowired
    ScheduleInfo scheduleInfo;

	@Autowired
	SessionInfo sessionInfo;

	/*
	 * 初期表示時
	 * 選択日:現在日時
     * スケジュール情報:DBから取得後セッションに格納（月単位）
	 */
    @GetMapping(value = "/calendarDisplay")
	public String calendarInitDisplay(Model model) {
	//public String calendarInitDisplay(Model model, @RequestParam("targetDate") String selectDate, HttpSession sessionInfo) {

		// ↓↓↓↓↓↓↓↓セッションからユーザー情報を取得
		// String userId = (String) sessionInfo.getAttribute("userId");
		// String usersGroupId = (String) sessionInfo.getAttribute("usersGroupId");
        String userId = "1";
		Integer groupId = null;

		// ↓↓↓↓↓↓↓↓パラメータから選択日を取得
		// String targetDate = selectDate;
		String targetDate = "20231109";

		if (StringUtils.isEmpty(targetDate)) {
			// パラメータの年月日が存在しない場合、今日日付をセット
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date currentDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			targetDate = sdf.format(currentDate);
		}

		// DBから対象スケジュール取得
		List <ScheduleInfoList> scheduleInfoList = new ArrayList <ScheduleInfoList>();
		scheduleInfoList = calendarDisplayService.selectTargetSchedule(userId, groupId, targetDate);

		// 対象スケジュール情報をDTOにセット
		scheduleInfo.setScheduleList(scheduleInfoList);
		// スケジュール情報をセッションに設定
		//sessionInfo.setAttribute("scheduleInfoList", scheduleInfoList);
		// 選択日をセット
		scheduleInfo.setTargetDate(targetDate);

		model.addAttribute("scheduleInfo", scheduleInfo);

		return "calendarDisplay";
	}

	// /*
	//  * 日付クリック時
	//  * 選択日:クリックした日時
    //  * スケジュール情報:セッションから取得（月単位）
	//  */
	// @GetMapping(value = "/calendarDisplay/Redisp")
	// public String calendarDisplayRedisp(Model model, RedirectAttributes redirectAttributes, @RequestParam("targetDate") String selectDate, HttpSession sessionInfo) {
		
	// 	// ↓↓↓↓↓↓↓↓セッションからユーザー情報を取得
	// 	// String userId = (String) sessionInfo.getAttribute("userId");
	// 	// String usersGroupId = (String) sessionInfo.getAttribute("usersGroupId");
	// 	// String userId = "1";
	// 	// Integer groupId = null;
		
	// 	// ↓↓↓↓↓↓↓↓パラメータから選択日を取得
	// 	// String targetDate = selectDate;
	// 	String targetDate = "20231101";

	// 	// セッションから対象スケジュール取得
	// 	List <ScheduleInfoList> scheduleInfoList = (List<ScheduleInfoList>) sessionInfo.getAttribute("scheduleInfoList");
	
	// 	// 対象スケジュール情報をDTOにセット
	// 	scheduleInfo.setScheduleList(scheduleInfoList);

	// 	// 選択日をセット
	// 	scheduleInfo.setTargetDate(targetDate);

	// 	model.addAttribute("scheduleInfo", scheduleInfo);

	// 	return "calendarDisplay";
	// }

	/*
	 * カレンダー切替時
	 * 選択日:なし
     * スケジュール情報:DBから取得後セッションに格納（月単位）
	 */
	@GetMapping(value = "/calendarDisplay/Switch")
	public String calendarDisplaySwitch(Model model) {
	//public String calendarDisplaySwitch(Model model, @RequestParam("targetMonth") String selectMonth, HttpSession sessionInfo) {

		// ↓↓↓↓↓↓↓↓セッションからユーザー情報を取得
		// String userId = (String) sessionInfo.getAttribute("userId");
		// String usersGroupId = (String) sessionInfo.getAttribute("usersGroupId");
		String userId = "1";
		Integer groupId = null;

		// ↓↓↓↓↓↓↓↓パラメータから選択年月を取得
		String targetMonth = "202312";

		// DBから対象スケジュール取得
		List <ScheduleInfoList> scheduleInfoList = new ArrayList <ScheduleInfoList>();
		scheduleInfoList = calendarDisplayService.selectTargetSchedule(userId, groupId, targetMonth);
	
		// 対象スケジュール情報をDTOにセット
		scheduleInfo.setScheduleList(scheduleInfoList);
		// スケジュール情報をセッションから削除
		((HttpSession) scheduleInfo).removeAttribute("scheduleInfoList");
		// スケジュール情報をセッションに設定
		//sessionInfo.setAttribute("scheduleInfoList", scheduleInfoList);

		model.addAttribute("scheduleInfo", scheduleInfo);

		return "calendarDisplay";
	}
}