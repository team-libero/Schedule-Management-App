package com.act.libero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScheduleDetailController {
    @RequestMapping("/scheduleDetail")
	public String scheduleDetail() {
		return "scheduleDetail";
	}
}
