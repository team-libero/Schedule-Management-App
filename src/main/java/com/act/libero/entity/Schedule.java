package com.act.libero.entity;

import java.util.Date;

import lombok.Data;

@Data
public class Schedule {

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
    /** 更新日 */
    private Date updatedAt;
}
