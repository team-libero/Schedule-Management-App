package com.act.libero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.act.libero.entity.User;
import com.act.libero.repository.UserMapper;

@Service
public class UserService {
    
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
}
