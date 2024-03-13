package com.act.libero.entity;

import java.util.Date;
import lombok.Data;

@Data
public class User {

    /** ユーザID */
    private String userId;
    /** 姓 */
    private String lastName;
    /** 名 */
    private String firstName;
    /** 権限 */
    private int authorityNo;
    /** グループID */
    private int usersGroupId;
    /** メールアドレス */
    private String email;
    /** パスワード */
    private String password;
    /** 最終ログイン日時 */
    private Date lastLoginAt;
    /** 削除フラグ */
    private int deleteFlag;
    /** 削除日時 */
    private Date deletedAt;
    /** 作成ユーザ */
    private String createdUserId;
    /** 作成日 */
    private Date createdAt;
    /** 更新ユーザ */
    private String updatedUserId;
    /** 更新日 */
    private Date updatedAt;

}
