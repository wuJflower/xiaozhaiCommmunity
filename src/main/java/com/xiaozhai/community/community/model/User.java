package com.xiaozhai.community.community.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String accountID;
    private String name;
    private String token;
    private Long gmtCreate;//时间戳
    private Long gmtModified;
    private String avatarUrl;

}
