package com.act.libero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.act.libero.dto.SessionInfo;
import com.act.libero.dto.UserEditInfo;
import com.act.libero.dto.UserInfo;
import com.act.libero.entity.User;
import com.act.libero.service.UserEditService;

@Controller
public class UserEditController {

  @Autowired
    protected SessionInfo sessionInfo;

	@Autowired
    UserEditService userEditService;

       /**
   * ユーザー情報登録画面を表示
   * @param model Model
   * @return ユーザー情報登録画面
   */
  @GetMapping(value = "/userInsert")
    public String initUserInsert(@ModelAttribute UserInfo userInfo, Model model) {

      User user = new User();
      model.addAttribute("userInfo", user);
      model.addAttribute("insertFlg", true);

      return "userEdit";
    }

   /**
   * ユーザー情報編集画面を表示
   * @param model Model
   * @return ユーザー情報編集画面
   */
  @GetMapping(value = "/userEdit")
    public String initUserEdit(@ModelAttribute UserInfo userInfo, Model model) {

    // String userId = sessionInfo.getUserId();
    // デバッグコード
    String userId = "abcde54321";

    User user = userEditService.selectUser(userId);
		model.addAttribute("userInfo", user);

    // ユーザ登録画面表示フラグ：false
    model.addAttribute("insertFlg", false);

      return "userEdit";
	}

  /**
   * 編集ボタン押下
   * @param userEditInfo ユーザー編集入力情報
   * @param model Model
   * @return ユーザー情報編集画面
   */
  @GetMapping(value = "/editUser")
    public String editUser(@ModelAttribute UserEditInfo userEditInfo, RedirectAttributes redirectAttributes, Model model) {

    // String userId = sessionInfo.getUserId();

    // ユーザー情報 存在チェック
    User user = userEditService.serectUserUpdatedAt(sessionInfo.getUserId(), userEditInfo.getUpdatedAt());
    if (user == null) {
      // ユーザテーブルから情報を取得できなかった場合
			redirectAttributes.addFlashAttribute("errorMessage", "対象のユーザが存在しません。もう一度やり直してください。");

			// 自画面を再表示
			return "redirect:/userEdit";
    }

    // 更新ユーザーIDをユーザー編集情報に追加
    userEditInfo.setUpdatedUserId(sessionInfo.getUserId());
    // ユーザー編集情報の更新
		if(!userEditService.updateUserEditInfo(userEditInfo)){
			// 更新に失敗した場合
			redirectAttributes.addFlashAttribute("errorMessage", "更新に失敗しました。時間をおいてお試しください。");
			// ログイン画面を再表示
			return "redirect:";
		}

    if (sessionInfo.getAuthorityNo() == 0) {
      // セッション.権限が"0"（管理者）の場合、ユーザー選択画面へ遷移
      return "redirect:/userSelect";
    } else {
      // 上記以外の場合、ユーザー編集画面へ遷移
      return "redirect:/userEdit";
    }
	}
}
