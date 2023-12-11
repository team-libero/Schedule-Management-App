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
    /** 氏名 */
    private String fullName;
    /** 権限 */
    private int authorityNo;
    /** グループID */
    private int usersGroupId;
    /** グループ名 */
    private int usersGroupName;
    
}
