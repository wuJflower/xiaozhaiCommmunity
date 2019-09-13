package com.xiaozhai.community.community.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Question {
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer commentCount;
    private Integer likeCount;
    private Integer viewCount;
    private String tag;
    private Integer creator;


}
