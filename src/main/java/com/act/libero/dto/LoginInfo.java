package com.act.libero.dto;

import lombok.Data;

@Data
public class LoginInfo {

    /** ユーザID */
    private String userid;
    /** パスワード */
    private String password;
}
