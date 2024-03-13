package com.act.libero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.act.libero.entity.User;
import com.act.libero.repository.UserMapper;

@Service
public class LoginService {

    /**
     * ユーザー情報 Mapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * ユーザー情報取得
     * return 検索結果
     */
    public User selectUserByPassword(String userId, String encryptedPassword) {
        return userMapper.selectUserByPassword(userId, encryptedPassword);
    }

    /**
     * 最終ログイン日時更新
     * return 更新結果（true：成功、false：失敗）
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserLastLoginAt(String userId) {
        try {
            return userMapper.updateUserLastLoginAt(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
