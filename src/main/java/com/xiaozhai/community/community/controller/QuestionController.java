package com.xiaozhai.community.community.controller;

import com.xiaozhai.community.community.dto.CommentDTO;
import com.xiaozhai.community.community.dto.QuestionDTO;
import com.xiaozhai.community.community.enums.CommentType;
import com.xiaozhai.community.community.service.CommentService;
import com.xiaozhai.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @Autowired
    CommentService commentService;


    @RequestMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        questionService.incView(id);
        QuestionDTO questionDTO = questionService.selectById(id);
        model.addAttribute("question",questionDTO);
//        获取问题一级评论
        List<CommentDTO> comments = commentService.listById(id, CommentType.QUESTION);
        model.addAttribute("comments",comments);
        return "question";
    }
}
