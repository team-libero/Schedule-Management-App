package com.act.libero.dto;

import java.io.Serializable;
import lombok.Data;
import java.util.Date;

@Data
public class ScheduleInfoList implements Serializable {
    
       /** スケジュールID */
       private int scheduleId;
       /** グループID */
       private int usersGroupId;
       /** ユーザID */
       private String userId;
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
       /** 通知可否 */
       private int announceFlag;
       /** 作成ユーザ */
       private String createdUserId;
       /** 作成日 */
       private Date createdAt;
       /** 更新ユーザ */
       private String updatedUserId;
       /** 更新日 */
       private Date updatedAt;
       /** 削除フラグ */
       private int deleteFlag;
       /** 削除日 */
       private Date deleteAt;
       /** 開始日付 */
       private String fromDate;

}
