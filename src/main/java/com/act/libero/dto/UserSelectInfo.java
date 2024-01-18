package com.act.libero.dto;

import java.io.Serializable;
import java.util.List;

import com.act.libero.entity.User;

import lombok.Data;

@Data
public class UserSelectInfo implements Serializable  {
    /** グループ名 */
    private String usersGroupName;
    /** ユーザ情報リスト */
    private List<User> userList;
    /** 選択ユーザID */
    private String selectUserId;
}
