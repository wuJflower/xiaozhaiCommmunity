package com.xiaozhai.community.community.controller;

import com.xiaozhai.community.community.dto.CommentDTO;
import com.xiaozhai.community.community.dto.ResultDTO;
import com.xiaozhai.community.community.exception.CustomizeErrorCode;
import com.xiaozhai.community.community.exception.CustomizedException;
import com.xiaozhai.community.community.model.Comment;
import com.xiaozhai.community.community.model.User;
import com.xiaozhai.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;

    //有RequestBody实现将前端json转换到CommentDTO
    @ResponseBody
    @RequestMapping(name = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (null == user){
            throw new CustomizedException(CustomizeErrorCode.USER_NOT_LOGIN);
        }

        Comment comment = new Comment();
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setCommentator(commentDTO.getCommentator());
        comment.setParentId(commentDTO.getParentId());
        commentService.insert(comment, user);
        //添加评论成功
        return new ResultDTO().okOf();
    }
}
