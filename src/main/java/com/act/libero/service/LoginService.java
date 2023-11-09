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
    public User selectUser(String userId) {
        return userMapper.selectUser(userId);
    }

    /**
     * ユーザー情報取得
     * return 検索結果
     */
    public User selectUserPassword(String userId, String encryptedPassword) {
        return userMapper.selectUserPassword(userId, encryptedPassword);
    }

    /**
     * ユーザー情報更新
     * return 更新結果（true：成功、false：失敗）
     */
    @Transactional(rollbackFor=Exception.class)
    public boolean UpdateUserLastLoginAt(String userId) {
        try {
            return userMapper.updateUserLastLoginAt(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * ユーザー情報登録
     * return 登録結果（true：成功、false：失敗）
     */
    @Transactional(rollbackFor=Exception.class)
    public boolean insertUser(User user) {
        try {
            userMapper.insertUser(user);
            return true;
        } catch (Exception e) {
            // model.addAttribute("errorMessage", "最終ログイン日時の更新に失敗しました。");
            e.printStackTrace();
            return false;
        }
    }
}

