package com.act.libero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalendarDisplayController {
    @RequestMapping("/calendarDisplay")
	public String calendarDisplay() {
		return "calendarDisplay";
	}
}
