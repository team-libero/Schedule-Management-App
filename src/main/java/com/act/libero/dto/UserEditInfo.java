package com.act.libero.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserEditInfo {
    /** ユーザID */
    private String userId;
    /** 姓 */
    private String sei;
    /** 名 */
    private String mei;
    /** メールアドレス */
    private String address;
    /** パスワード */
    private String password1;
    /** パスワード(確認) */
    private String password2;
    /** 権限 */
    private int authorityNo;
    /** 更新日 */
    private Date updatedAt;
    /** 更新ユーザーID */
    private String updatedUserId;
}
