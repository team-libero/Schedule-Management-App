package com.act.libero.dto;

import lombok.Data;

@Data
public class UserMember {

    /** ユーザID */
    private String userId;

    /** 姓 */
    private String lastName;

    /** 名 */
    private String firstName;

    /** 更新日 */
    private String updatedAt;
}
