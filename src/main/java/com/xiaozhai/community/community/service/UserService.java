package com.xiaozhai.community.community.service;

import com.xiaozhai.community.community.mapper.UserMapper;
import com.xiaozhai.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void insertOrUpdate(User user) {
        User dbuser = userMapper.finByAccountId(Integer.valueOf(user.getAccountID()));
        if (dbuser == null) {
            userMapper.insertUser(user);
        }
        else {
            userMapper.updateUser(user.getId(),user.getGmtModified(),user.getAvatarUrl(),user.getName(),user.getToken());
        }
    }
}
