package com.act.libero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.act.libero.dto.ScheduleEditInfo;
import com.act.libero.entity.ScheduleEdit;
import com.act.libero.service.ScheduleEditService;
import com.act.libero.util.ScheduleEditConst;

import jakarta.servlet.http.HttpSession;

@Controller
public class ScheduleEditController {

	@Autowired
	ScheduleEditService scheduleEditService;

	@RequestMapping("/scheduleEdit")
	public String scheduleEdit(Model model, @RequestParam(name = "scheduleId", required = false) Integer scheduleId,
			@RequestParam(name = "date", required = false) String date,
			@RequestParam(name = "calendarType", required = false) String calendarType, HttpSession session) {
		ScheduleEditInfo schedule = new ScheduleEditInfo();
		// 編集
		if (scheduleId != null) {
			schedule = scheduleEditService.initEdit(scheduleId);
			session.setAttribute("scheduleId", scheduleId);
		// 登録
		} else {
			schedule = scheduleEditService.initRegist(date, calendarType);
			session.setAttribute("date", date);
			session.setAttribute("calendarType", calendarType);
		}
		model.addAttribute("items", schedule);
		return "scheduleEdit";
	}

	@RequestMapping("/scheduleEdit/register")
	public String scheduleRegister(@ModelAttribute ScheduleEdit scheduleEdit, Model model, RedirectAttributes redirectAttributes, HttpSession session) {
		String flg = scheduleEditService.register(scheduleEdit, session);
		model.addAttribute("scheduleId", scheduleEdit.getScheduleId());
		// 編集の場合
		if (flg.equals(ScheduleEditConst.EDIT_SUCCSESS) || flg.equals(ScheduleEditConst.EDIT_FAIL)) {
			redirectAttributes.addAttribute("scheduleId",scheduleEdit.getScheduleId());
			if(flg.equals(ScheduleEditConst.EDIT_FAIL)) {
				model.addAttribute("errMsg", "更新に失敗しました。しばらくたってから再実施してください。");
			}
			return "redirect:/scheduleDetail";
		}
		// 登録の場合
		redirectAttributes.addAttribute("scheduleYMD", session.getAttribute("date"));
		redirectAttributes.addAttribute("calendarType", session.getAttribute("calendarType"));
		if(flg.equals(ScheduleEditConst.REGIST_FAIL)) {
			model.addAttribute("errMsg", "登録に失敗しました。しばらくたってから再実施してください。");
		}
		return "redirect:/calendarDisplay";
	}
}
