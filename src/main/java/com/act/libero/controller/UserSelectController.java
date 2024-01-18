package com.act.libero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserSelectController {
    @RequestMapping("/userSelect")
	public String userSelect() {
		return "userSelect";
	}
}