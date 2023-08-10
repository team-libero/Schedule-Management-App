package com.act.libero.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.act.libero.dto.ScheduleInfo;
import com.act.libero.dto.SessionInfo;
import com.act.libero.service.ScheduleDetailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ScheduleDetailController {

  @Autowired
  protected SessionInfo sessionInfo;

  @Autowired
  ScheduleDetailService scheduleDetailService;

  /**
   * スケジュール詳細画面を表示
   * 
   * @param model Model
   * @return スケジュール詳細画面
   */
  @GetMapping(value = "/scheduleDetail")

  public String scheduleDetail(@ModelAttribute ScheduleInfo scheduleInfo, Model model,
      @RequestParam("scheduleId") int scheduleId, @RequestParam("scheduleYMD") String scheduleYMD,
      @RequestParam("calenderType") String calenderType, RedirectAttributes redirectAttrs, HttpSession session) {

    // Service呼び出し
    ScheduleInfo output = scheduleDetailService.selectSchedule(scheduleId, scheduleYMD, calenderType, session);

    if (!StringUtils.isEmpty(output.getErrMsg())) {
      model.addAttribute("errMsg", output.getErrMsg());
      return "forward:calendarDisplay";
    }
    model.addAttribute("scheduleInfo", output);
    return "scheduleDetail";
  }

  /**
   * スケジュール削除処理
   * 
   * @param model Model
   * @return スケジュール詳細画面
   */
  @GetMapping(value = "/scheduleDetail/delete")

  public String deleteSchedule(@ModelAttribute ScheduleInfo scheduleInfo, Model model,
      RedirectAttributes redirectAttributes, @RequestParam("scheduleId") int scheduleId, HttpSession session) {

    // 削除対象存在チェック
    boolean flg = scheduleDetailService.chkScheduleExist(scheduleId,session);
    if (!flg) {
      return "redirect:/scheduleDetail";
    }

    // スケジュール削除
    scheduleDetailService.deleteSchedule(scheduleId, session);
    return "redirect:/calendarDisplay";
  }

  /**
   * スケジュール編集画面へ進む
   * 
   * @param model Model
   * @return スケジュール詳細画面
   */
  @GetMapping(value = "/scheduleDetail/edit")
  public String editSchedule(Model model, @RequestParam("scheduleId") int scheduleId,
      @RequestParam("calenderType") String calenderType) {
    model.addAttribute("scheduleId", scheduleId);
    model.addAttribute("calenderType", calenderType);
    return "forward:scheduleEdit";
  }
}
