package com.act.libero.entity;

import lombok.Data;

@Data
public class UserSelect {

    /** グループ名 */
    private String usersGroupName;

    /** ユーザID */
    private String userId;

    /** 姓 */
    private String lastName;

    /** 名 */
    private String firstName;

    /** 更新日 */
    private String updatedAt;

}
