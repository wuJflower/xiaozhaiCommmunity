package com.xiaozhai.community.community.service;

import com.sun.management.OperatingSystemMXBean;
import com.xiaozhai.community.community.mapper.UserMapper;
import com.xiaozhai.community.community.model.User;
import com.xiaozhai.community.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void insertOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            userMapper.insert(user);
        }
        else {
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setAvatarUrl(dbUser.getAvatarUrl());
            updateUser.setName(dbUser.getName());
            updateUser.setToken(dbUser.getToken());
            updateUser.setGmtModified(System.currentTimeMillis());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andIdEqualTo(dbUser.getId());
<<<<<<< HEAD
=======
            //注意是选择性更新表元素属性，updateByExampleSelective,不是updateByExample
>>>>>>> e77cf45f42657b7f1b98800bd368630c457da6d7
            userMapper.updateByExampleSelective(updateUser,example);
        }
    }
}
