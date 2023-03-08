package com.act.libero.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserEditController {
    @RequestMapping("/userEdit")
	public String userEdit() {
		return "userEdit";
	}
}
