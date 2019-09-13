package com.xiaozhai.community.community.controller;

import com.xiaozhai.community.community.dto.PagenationDTO;
import com.xiaozhai.community.community.dto.QuestionDTO;
import com.xiaozhai.community.community.exception.CustomizedException;
import com.xiaozhai.community.community.mapper.QuestionMapper;
import com.xiaozhai.community.community.mapper.UserMapper;
import com.xiaozhai.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

//将该类标识为bean
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/")
    public String indexController(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "3") int size,
            HttpServletRequest request,
            Model model
    ) {

        List<QuestionDTO> questionDTOS = questionService.questionList(page, size);
        Integer totalPage = 0;
        Integer totalCount = questionMapper.countQuestion();
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if(page > totalPage){
            throw new CustomizedException("您查询的页面不存在");
        }
        PagenationDTO pagenationDTO =new PagenationDTO();
        pagenationDTO.setQuestionDTOS(questionDTOS);
        pagenationDTO.setPage(page);
        pagenationDTO.setTotalPage(totalPage);
        model.addAttribute("pagenation",pagenationDTO);
        return "index";
    }
}
