package com.xiaozhai.community.community.service;

import com.xiaozhai.community.community.enums.CommentType;
import com.xiaozhai.community.community.exception.CustomizeErrorCode;
import com.xiaozhai.community.community.exception.CustomizedException;
import com.xiaozhai.community.community.mapper.CommentMapper;
import com.xiaozhai.community.community.model.Comment;
import com.xiaozhai.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    public void insert(Comment comment , User user) {
//      选择的问题或者评论不存在
        if (comment.getId() == null || comment.getParentId() == null){
            throw new CustomizedException(CustomizeErrorCode.TARGET_NOT_SELECTED);
        }
//        评论类型非法
        if ( comment.getType()==null ||!CommentType.isExist(comment.getType())){
            throw new CustomizedException(CustomizeErrorCode.COMMENT_TYPE_ILLEGAL);
        }
        commentMapper.insert(comment);
    }
}
