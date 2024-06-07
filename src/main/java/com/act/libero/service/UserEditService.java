package com.act.libero.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.act.libero.dto.UserEditInfo;
import com.act.libero.entity.User;
import com.act.libero.repository.UserMapper;

@Service
public class UserEditService {
    
    /**
     * ユーザー情報 Mapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * ユーザー情報取得
     * return 検索結果
     */
    public User selectUser(String userId) {
        return userMapper.selectUser(userId);
    }

    /**
     * ユーザー情報 存在チェック
     * return 検索結果
     */
    public User selectUserUpdatedAt(String userId, Date updatedAt) {
        return userMapper.selectUserUpdatedAt(userId, updatedAt);
    }

    /**
     * ユーザー情報更新
     * return 更新結果（true：成功、false：失敗）
     */
    @Transactional(rollbackFor=Exception.class)
    public boolean updateUserEditInfo(UserEditInfo userEditInfo) {
        try {
            return userMapper.updateUserEditInfo(userEditInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
