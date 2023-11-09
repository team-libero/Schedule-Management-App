package com.act.libero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.act.libero.dto.ScheduleEditInfo;
import com.act.libero.service.ScheduleEditService;


@Controller
public class ScheduleEditController {

  @Autowired
  ScheduleEditService scheduleEditService;

    @RequestMapping("/scheduleEdit")
	public String scheduleEdit(Model model, @RequestParam(value = "scheduleId", required = false) Integer scheduleId) {
		ScheduleEditInfo schedule = scheduleEditService.init(scheduleId);
		model.addAttribute("items",schedule);
		return "scheduleEdit";
	}
}
