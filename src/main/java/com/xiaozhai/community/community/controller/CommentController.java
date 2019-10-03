package com.xiaozhai.community.community.controller;

import com.xiaozhai.community.community.dto.CommentDTO;
import com.xiaozhai.community.community.dto.ResultDTO;
import com.xiaozhai.community.community.enums.CommentType;
import com.xiaozhai.community.community.exception.CustomizeErrorCode;
import com.xiaozhai.community.community.exception.CustomizedException;
import com.xiaozhai.community.community.model.Comment;
import com.xiaozhai.community.community.model.User;
import com.xiaozhai.community.community.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
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
        if (null == user) {
            log.info("用户未登录");
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

    /*
        二级评论接口
     */
    @ResponseBody
    @GetMapping("/comment/{id}")
    public ResultDTO<List<CommentDTO>> comments(@PathVariable Integer id) {
        List<CommentDTO> commentDTOS = commentService.listById(id, CommentType.COMMENT);
        return new ResultDTO().okOf(commentDTOS);
    }

}
