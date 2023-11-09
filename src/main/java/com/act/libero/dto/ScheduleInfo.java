package com.act.libero.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class ScheduleInfo implements Serializable {

    /** エラーメッセージ */
    private String errMsg;
    /** スケジュールID */
    private Integer scheduleId;
    /** スケジュール年月日 */
    private String scheduleYmd;
    /** カレンダー種別 */
    private String calendarType;
    /** タイトル */
    private String titleName;
    /** 開始日付 */
    private Date fromDateTime;
    /** 終了日付 */
    private Date toDateTime;
    /** 場所 */
    private String address;
    /** 説明 */
    private String memo;
    /** グループID */
    private Integer groupId;
    /** 姓 */
    private String lastName;
    /** 名 */
    private String firstName;
    /** ユーザID */
    private String userId;
    /** スケジュール区分 */
    private String scheduleKbn;
    /** 更新日 */
    private Date updatedAt;
    /** 開始日付 */
    private String fromDate;
    /** 開始時間 */
    private String fromTime;
    /** 開始日付の曜日 */
    private String dayOfWeekFrom;
    /** 終了日付 */
    private String toDate;
    /** 終了時間 */
    private String toTime;
    /** 終了日付の曜日 */
    private String dayOfWeekTo;
}
