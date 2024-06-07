package com.act.libero.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.act.libero.dto.UserMember;
import com.act.libero.dto.UserSelectInfo;
import com.act.libero.entity.UserSelect;
import com.act.libero.repository.UserSelectMapper;

@Service
public class UserSelectService {
    /**
     * ユーザー情報 Mapper
     */
    @Autowired
    private UserSelectMapper userSelectMapper;

    // @Autowired
    // private UserSelectInfo userSelectInfo;

    /**
     * ログインユーザのグループ情報取得
     * 
     * @param groupId グループID
     * @return
     */
    public UserSelectInfo selectGroupInfo(String groupId) {
        // グループ情報取得
        List<UserSelect> userSelectList = userSelectMapper.selectGroupInfo(groupId);
        List<UserMember> userMemberList = new ArrayList<>();
        UserSelectInfo userSelectInfo = new UserSelectInfo();

        if (!userSelectList.isEmpty()) {

            for (UserSelect userSelect : userSelectList) {
                UserMember userMember = new UserMember();
                userMember.setUserId(userSelect.getUserId());
                userMember.setFirstName(userSelect.getFirstName());
                userMember.setLastName(userSelect.getLastName());
                userMember.setUpdatedAt(userSelect.getUpdatedAt());

                userMemberList.add(userMember);
                
            }
            // ユーザグループ名
            userSelectInfo.setUsersGroupName(userSelectList.get(0).getUsersGroupName());
            // ユーザメンバー
            userSelectInfo.setUserMember(userMemberList);

        }

        return userSelectInfo;
    }

    /**
     * ユーザ削除
     * 
     * @param loginUserId ログインユーザID
     * @param userId      ユーザID
     * @param updateAt    更新日
     * @return
     */
    public String userDelete(String loginUserId, String userId, String updateAt) {
        String resultMessage = null;

        /* 存在チェック */
        UserSelect userSelect = userSelectMapper.presenceCheck(userId, updateAt);
        if (Objects.isNull(userSelect.getUserId())) {
            resultMessage = "対象のユーザが存在しません。もう一度やり直してください。";
            return resultMessage;
        }

        /* ユーザ削除 */
        int resultNum = userSelectMapper.deleteUser(loginUserId, userId, updateAt);
        if (resultNum == 0) {
            resultMessage = "削除に失敗しました。時間をおいてお試しください。";
        }

        return resultMessage;
    }

}
