package com.xiaozhai.community.community.dto;

import com.xiaozhai.community.community.model.User;
import lombok.Data;

/**
 * 用于后端和前端问题详情的传输
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer commentCount;
    private Integer likeCount;
    private Integer viewCount;
    private String tag;
    private Integer creator;
    private User user;
}
