package com.act.libero.dto;

import java.io.Serializable;
import lombok.Data;
import java.util.Date;

@Data
public class UserInfo implements Serializable {
    
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
    private String created_user_id;
    /** 作成日 */
    private Date created_at;
    /** 更新ユーザ */
    private String updated_user_id;
    /** 更新日 */
    private Date updated_at;

}
