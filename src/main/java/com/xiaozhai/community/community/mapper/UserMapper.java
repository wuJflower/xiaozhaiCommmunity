package com.xiaozhai.community.community.mapper;

import com.xiaozhai.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_id,name,token,gmt_create,gmt_modified,avatar_url) values (#{accountID},#{name},#{token},#{gmtCreate},#{gmtModified}, #{avatarUrl})")
    void insertUser(User user);

    @Select("select * from user where token =  #{token}")
    User findByToken(String token);

    @Select("select * from user where id =  #{id}")
    User finById(Integer id);
}
