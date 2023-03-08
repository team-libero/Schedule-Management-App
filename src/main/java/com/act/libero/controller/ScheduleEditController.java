package com.act.libero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScheduleEditController {
    @RequestMapping("/scheduleEdit")
	public String scheduleEdit() {
		return "scheduleEdit";
	}
}
