package com.xiaozhai.community.community.controller;

import com.xiaozhai.community.community.dto.QuestionDTO;
import com.xiaozhai.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;


    @RequestMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id,
                           Model model) {
        QuestionDTO questionDTO = questionService.selectById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
