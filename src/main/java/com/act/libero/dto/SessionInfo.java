package com.act.libero.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SessionInfo {

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
    
}
