package com.xiaozhai.community.community.mapper;

import com.xiaozhai.community.community.model.Question;
import com.xiaozhai.community.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionEtxMapper {
    int incView(Question record);

    void incCommentCount(Question record);
}