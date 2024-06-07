package com.act.libero.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserSelectInfo implements Serializable {

    // /** グループID */
    private String usersGroupName;

    /** ユーザID */
    private String userId;

    /** 姓 */
    private String lastName;

    /** 名 */
    private String firstName;

    /** 更新日 */
    private String updated_at;

    /* ユーザメンバー */
    private List<UserMember> userMember;

    /** ユーザID（ラジオボタン選択） */
    private String checkedRadioUserId;

}
