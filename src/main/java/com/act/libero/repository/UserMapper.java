package com.act.libero.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.act.libero.entity.User;

@Mapper
public interface UserMapper {
    User selectUser(String userId);

    User selectUserPassword(String userId, String encryptedPassword);

    boolean updateUserLastLoginAt(String userId);

    void insertUser(@Param("user") User user);
}
