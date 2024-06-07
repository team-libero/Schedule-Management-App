package com.act.libero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.act.libero.dto.UserSelectInfo;
import com.act.libero.service.UserSelectService;

import jakarta.servlet.http.HttpSession;

/*
 * ユーザ選択画面
 */
@Controller
public class UserSelectController {

	@Autowired
	UserSelectService userSelectService;

	// @Autowired
	// protected SessionInfo sessionInfo;

	@RequestMapping("/userSelect")
	public String userSelect(HttpSession session, @ModelAttribute UserSelectInfo userSelect, Model model) {
		String groupId =  "1";//(String) session.getAttribute("groupId");

		// List<UserSelectInfo> userSelectInfo
		userSelect = userSelectService.selectGroupInfo(groupId);
		// userSelect.setUsersGroupName(userSelectInfo.getUsersGroupName());

		model.addAttribute("userSelect", userSelect);
		return "userSelect";
	}

	/**
	 * ユーザ新規登録
	 * 
	 * @return
	 */
	public String register(HttpSession session) {

		return "userSelect";
	}

	/**
	 * ユーザ編集
	 * 
	 * @return
	 */
	@PostMapping("/editUserSelect")
	public String edit(@ModelAttribute UserSelectInfo userSelectInfo, RedirectAttributes redirectAttributes) {

		// ユーザテーブルから情報を取得できなかった場合
		redirectAttributes.addFlashAttribute("userId", userSelectInfo.getCheckedRadioUserId());

		return "redirect:/userEdit";
	}

	/**
	 * ユーザ削除
	 * 
	 * @return
	 */
	@RequestMapping("delete")
	public String delete(@ModelAttribute UserSelectInfo userSelect, Model model, HttpSession session) {

		String resultMessage = userSelectService.userDelete(session.getId() ,userSelect.getUserId(), userSelect.getUpdated_at());
		model.addAttribute(resultMessage, resultMessage);
		return "userSelect";
	}
}
