package com.xiaozhai.community.community.service;

import com.xiaozhai.community.community.dto.QuestionDTO;
import com.xiaozhai.community.community.mapper.QuestionMapper;
import com.xiaozhai.community.community.mapper.UserMapper;
import com.xiaozhai.community.community.model.Question;
import com.xiaozhai.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public  List<QuestionDTO> questionList(int page, int size) {
        Integer offset = size * (page -1);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        questions= questionMapper.questionByPage(offset, size);
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            Integer creator = question.getCreator();
            User user = userMapper.finById(creator);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }

    public List<QuestionDTO> questionListById(Integer id, int page, int size) {
        Integer offset = size * (page -1);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        questions = questionMapper.questionByPageAndId(id, offset, size);
        for (Question question : questions) {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            Integer creator = question.getCreator();
            User user = userMapper.finById(creator);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }


    public QuestionDTO selectById(Integer id) {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO=questionMapper.questionById(id);
        Integer creator = questionDTO.getCreator();
        User user = userMapper.finById(creator);
        questionDTO.setUser(user);
        return questionDTO;
    }
}
