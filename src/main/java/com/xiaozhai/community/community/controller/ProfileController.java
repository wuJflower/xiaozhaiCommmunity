package com.xiaozhai.community.community.controller;

import com.xiaozhai.community.community.dto.PagenationDTO;
import com.xiaozhai.community.community.dto.QuestionDTO;
import com.xiaozhai.community.community.mapper.QuestionMapper;
import com.xiaozhai.community.community.mapper.UserMapper;
import com.xiaozhai.community.community.model.QuestionExample;
import com.xiaozhai.community.community.model.User;
import com.xiaozhai.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;

    private User user = null;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page", required = false,defaultValue = "1") int page,
                          @RequestParam(name = "size", required = false, defaultValue = "3") int size,
                          HttpServletRequest request,
                          Model model){

        User user = (User) request.getSession().getAttribute("user");
        if (null == user){
            return "redirect:/";
        }
        if ("questions".equals(action)){
        model.addAttribute("section", "questions");
        model.addAttribute("sectionName", "我的问题");
        }else if ("replies".equals(action)){
        model.addAttribute("section", "replies");
        model.addAttribute("sectionName", "我的回复");
        }
        List<QuestionDTO> questionDTOS = questionService.questionListById(user.getId(),page, size);
        Integer totalPage = 0;

        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(user.getId());
        Integer totalCount = (int)questionMapper.countByExample(example);
        if (totalCount % size == 0) { 
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        IndexController.fillPagenation(page, model, questionDTOS, totalPage);
        return "profile";
    }
}
