package com.act.libero.repository;

import org.apache.ibatis.annotations.Mapper;

import com.act.libero.entity.User;

@Mapper
public interface UserMapper {
    User selectUser(String userId);
}
