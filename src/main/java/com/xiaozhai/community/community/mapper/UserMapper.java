package com.xiaozhai.community.community.mapper;

import com.xiaozhai.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified) values (#{accountID},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insertUser(User user);
}
