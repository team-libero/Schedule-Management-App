package com.act.libero.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.act.libero.dto.UserEditInfo;
import com.act.libero.entity.User;

@Mapper
public interface UserMapper {
    User selectUser(String userId);

    User serectUserUpdatedAt(String userId, Date updatedAt);

    boolean updateUserEditInfo(UserEditInfo userEditInfo);

    List<User> selectUserList(int GroupId);
}
