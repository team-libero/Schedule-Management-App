package com.act.libero.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.libero.dto.ScheduleEditInfo;
import com.act.libero.entity.ScheduleEdit;
import com.act.libero.repository.ScheduleEditMapper;
import com.act.libero.util.ScheduleEditConst;

import jakarta.servlet.http.HttpSession;

@Service
public class ScheduleEditServiceImpl implements ScheduleEditService {

    /**
     * スケジュール情報情報 Mapper
     */
    @Autowired
    private ScheduleEditMapper scheduleEditMapper;

    private boolean editFlg = false;

    /**
     * 初期表示_編集
     */
    @Override
    public ScheduleEditInfo initEdit(Integer scheduleId) {
        ScheduleEditInfo result = new ScheduleEditInfo();
        if (scheduleId != null) {
            ScheduleEdit items = scheduleEditMapper.getScheduleInfo(scheduleId);
            result = createInitItem(items);
        }
        editFlg = true;
        return result;
    }

    /**
     * 初期表示_登録
     */
    @Override
    public ScheduleEditInfo initRegist(String date, String calendarType) {
        ScheduleEditInfo result = new ScheduleEditInfo();
        String dateStr = date;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dates = new Date();
        try {
            dates = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        result.setFromDateTime(dates);
        result.setToDateTime(dates);
        result.setCalendarType(calendarType);
        editFlg = false;
        return result;
    }

    /**
     * 登録ボタン押下時の処理
     */
    @Override
    public String register(ScheduleEdit scheduleEdit, HttpSession session) {
        session.setAttribute("userId", "test111"); //TODO 動確用
        String userId = session.getAttribute("userId").toString();
        scheduleEdit.setUserId(userId);
        scheduleEdit.setCreatedUserId(userId);
        scheduleEdit.setUpdatedUserId(userId);
        // 編集
        if (editFlg) {
            int scheduleId = (int) session.getAttribute("scheduleId");
            scheduleEdit.setScheduleId(scheduleId);
            try {
                scheduleEditMapper.edit(scheduleEdit);
            } catch(Exception e) {
                return ScheduleEditConst.EDIT_FAIL;
            }
            return ScheduleEditConst.EDIT_SUCCSESS;
        // 登録
        } else {
            try {
                scheduleEditMapper.register(scheduleEdit);
            } catch(Exception e) {
                return ScheduleEditConst.REGIST_FAIL;
            }
            return ScheduleEditConst.REGIST_SUCCSESS;
        }
    }

    /**
     * LINE通知処理
     */
    @Override
    public String lineNotify(ScheduleEdit scheduleEdit){
        // メッセージ作成
        String message = "\n【タイトル】" + scheduleEdit.getTitleName() + "\n【日時】" +
        scheduleEdit.getFromDateTime() + "～" + scheduleEdit.getToDateTime() + "\n【場所】" +
        scheduleEdit.getAddress() + "\n【説明】" + scheduleEdit.getMemo();

        int groupId = scheduleEdit.getGroupId();
        String token = scheduleEditMapper.getLineToken(groupId);
        if (token.equals(null) || token.equals("")){
            return "tokenError";
        }
        String apiUrl = "https://notify-api.line.me/api/notify";
        try {
            sendLineNotify(token, apiUrl, message);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 初期表示_編集 画面項目作成用
     */
    private ScheduleEditInfo createInitItem(ScheduleEdit se) {
        ScheduleEditInfo items = new ScheduleEditInfo();
        String fromDateStr = se.getFromDateTime();
        String toDateStr = se.getToDateTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fromDates = new Date();
        Date toDates = new Date();
        try {
            fromDates = formatter.parse(fromDateStr);
            toDates = formatter.parse(toDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        items.setScheduleId(se.getScheduleId());
        items.setGroupId(se.getGroupId());
        items.setTitleName(se.getTitleName());
        items.setFromDateTime(fromDates);
        items.setToDateTime(toDates);
        items.setAddress(se.getAddress());
        items.setMemo(se.getMemo());
        items.setAnnounceFlag(se.getAnnounceFlag());
        return items;
    }

    /**
     * LINE通知送信HTTPリクエストメソッド
     */
    private static void sendLineNotify(String token, String apiUrl, String message) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .header("Authorization", "Bearer " + token)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .POST(HttpRequest.BodyPublishers.ofString("message=" + URLEncoder.encode(message, StandardCharsets.UTF_8)))
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}
