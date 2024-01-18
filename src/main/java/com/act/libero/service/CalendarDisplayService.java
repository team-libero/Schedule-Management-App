package com.act.libero.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.act.libero.dto.ScheduleInfoList;
import com.act.libero.repository.CalendarDisplayMapper;

@Service
public class CalendarDisplayService {
    
    /**
     * カレンダー表示情報 Mapper
     */
    @Autowired
    private CalendarDisplayMapper calendarDisplayMapper;

	/**
     * スケジュール情報取得
     * return 検索結果
     */
    public List <ScheduleInfoList> selectTargetSchedule(String userId, Integer groupId, String targetDate) {

        // DB検索用に対象日を加工
        String targetYM = targetDate.substring(0, 4) + "-" + targetDate.substring(4, 6) + "%";

        // 表示するスケジュール情報を取得する
        List <ScheduleInfoList> tgtScheduleInfoList = new ArrayList <ScheduleInfoList>();
        if (Objects.isNull(groupId)) {
			tgtScheduleInfoList = calendarDisplayMapper.selectTargetSchedule(userId, targetYM);
		} else {
			tgtScheduleInfoList = calendarDisplayMapper.selectTargetScheduleGrp(groupId, targetYM);
		}

        // 取得結果に開始日付（Stringをセット）
		for (ScheduleInfoList sil : tgtScheduleInfoList) {
			String str = new SimpleDateFormat("yyyyMMdd").format(sil.getFromDateTime());
			sil.setFromDate(str);
		}

        return tgtScheduleInfoList;
    }

    /**
     * カレンダー情報取得
     * return 検索結果
     */
    // public void outputCalendar(int inputYear, int inputMonth, int inputDay) {

    //     TargetMonth calendarDisplayInfo = new TargetMonth();

    //     Calendar cl = Calendar.getInstance();
		
	// 	// String[] month = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	// 	// String[] week = {"SUN","MON","TUE","WED","THU","FRI","SAT"};
		
	// 	int firstDay = 1; //月の初日
	// 	//月の初日の曜日(日曜日=1、月曜日=2であるため-1をして配列weekの添字に使用)
	// 	cl.set(inputYear,inputMonth,firstDay);
	// 	int firstDayWeek = cl.get(Calendar.DAY_OF_WEEK) - 1; 
	// 	int lastDay = cl.getActualMaximum(Calendar.DAY_OF_MONTH); //月の最終日

    //     calendarDisplayInfo.setYear(inputYear);
    //     calendarDisplayInfo.setMonth(inputMonth+1);

	// 	// System.out.print("     " + month[inputMonth] + " " +  inputYear); //年と月を出力
	// 	// System.out.println(); 

	// 	// //カレンダーの曜日を出力
	// 	// for (int i = 0; i < week.length; i++) {
	// 	// 	System.out.printf("%2s ",week[i]);
	// 	// }
	// 	// System.out.println();
	// 	// //初日が何曜日かに合わせて空白を出力
	// 	// System.out.print(String.join("", Collections.nCopies(firstDayWeek, "   ")));
	// 	//日付を出力
    //     ArrayList<Integer> list = new ArrayList<Integer>();
	// 	for (int i = firstDay; i <= lastDay; i++) {
    //         list.add(i + 1);
	// 		// System.out.printf("%2d ",i);
	// 		// if ((firstDayWeek + i) % 7 == 0) {
	// 		// 	System.out.println();
	// 		// }			
	// 	}
    //     calendarDisplayInfo.setDay(list);
    // }
}
