package com.act.libero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.act.libero.dto.SessionInfo;
import com.act.libero.dto.UserSelectInfo;
import com.act.libero.service.UserSelectService;

@Controller
public class UserSelectController {

    @Autowired
    protected SessionInfo sessionInfo;

	@Autowired
    UserSelectService userSelectService;

	private static final int AUTHORITY_NO_KANRISHA = 0;
	// private static final int AUTHORITY_NO_IPPAN = 1;
	
    @RequestMapping("/userSelect")
	public String userSelect(@ModelAttribute UserSelectInfo userSelectInfo, Model model) {

		// ユーザの権限が管理者の場合
		if (sessionInfo.getAuthorityNo() == AUTHORITY_NO_KANRISHA) {

			//ユーザーテーブルからデータ取得
		// List<User> userList = userSelectService.selectUserList(sessionInfo.getUsersGroupId());

			// ユーザ編集画面（ユーザ選択）を表示
			return "userSelect";

		// ユーザの権限が一般の場合
		} else {
			// ユーザ編集画面へ遷移
			return "forward:userEdit";
		}


		// return "userSelect";
	}
}
