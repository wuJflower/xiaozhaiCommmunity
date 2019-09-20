package com.xiaozhai.community.community.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private int commentator;
    private String content;
    private long viewCount;
    private long likeCount;
    private int type;
    private Integer parentId;
}
