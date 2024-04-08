package com.act.libero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.act.libero.dto.UserInfo;
import com.act.libero.entity.User;
import com.act.libero.service.UserService;

@Controller
public class UserEditController {

	@Autowired
    UserService userService;

   /**
   * ユーザー情報検索画面を表示
   * @param model Model
   * @return ユーザー情報一覧画面
   */
  @GetMapping(value = "/userEdit")
    
    public String index(@ModelAttribute UserInfo userInfo, Model model) {

    String userId = "1";

    User user = userService.selectUser(userId);
		model.addAttribute("userInfo", user);

      return "userEdit";
	}
}
