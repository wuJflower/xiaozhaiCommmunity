package com.xiaozhai.community.community.dto;

import lombok.Data;

import java.util.List;
@Data
public class PagenationDTO {
    private List<QuestionDTO> questionDTOS;
    private Integer page;
    private Integer totalPage;
}
