package com.act.libero.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.act.libero.entity.UserSelect;

@Mapper
public interface UserSelectMapper {

    /** ログインユーザのグループ情報取得 */
    public List<UserSelect> selectGroupInfo(String groupId);

    /** ユーザの存在チェック */
    UserSelect presenceCheck(String userId, String updateAt);

    /** ユーザ削除 */
    int deleteUser(String loginUserId, String userId, String updateAt);

}
