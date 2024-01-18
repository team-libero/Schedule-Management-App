package com.act.libero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.act.libero.dto.ScheduleEditInfo;
import com.act.libero.entity.ScheduleEdit;
import com.act.libero.service.ScheduleEditService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ScheduleEditController {

	@Autowired
	ScheduleEditService scheduleEditService;

	@RequestMapping("/scheduleEdit")
	public String scheduleEdit(Model model, @RequestParam(value = "scheduleId", required = false) Integer scheduleId,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "calendarType", required = false) String calendarType, HttpSession session) {
		ScheduleEditInfo schedule = new ScheduleEditInfo();
		if (scheduleId != null) {
			schedule = scheduleEditService.initEdit(scheduleId);
			session.setAttribute("scheduleId", scheduleId);
		} else {
			schedule = scheduleEditService.initRegist(date, calendarType);
		}
		model.addAttribute("items", schedule);
		return "scheduleEdit";
	}

	@RequestMapping("/scheduleEdit/register")
	public String scheduleRegister(@ModelAttribute ScheduleEdit scheduleEdit, Model model, HttpSession session) {
		scheduleEditService.register(scheduleEdit, session);
		model.addAttribute("scheduleId", scheduleEdit.getScheduleId());
		return "scheduleDetail";
	}
}
