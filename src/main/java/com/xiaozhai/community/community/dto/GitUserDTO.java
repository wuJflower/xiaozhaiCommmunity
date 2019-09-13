package com.xiaozhai.community.community.dto;

import lombok.Data;

@Data
public class GitUserDTO {
    private String login;
    private Long id;
    private String bio;
    private String avatar_url;

}
