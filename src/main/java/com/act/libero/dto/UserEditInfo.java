package com.act.libero.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserEditInfo {
    /** ユーザID */
    private String userId;
    /** 姓 */
    private String lastName;
    /** 名 */
    private String firstName;
    /** メールアドレス */
    private String email;
    /** パスワード */
    private String password;
    /** パスワード(確認) */
    private String passwordConfirm;
    /** 権限 */
    private int authorityNo;
    /** 更新日 */
    private Date updatedAt;
    /** 更新ユーザーID */
    private String updatedUserId;
}
