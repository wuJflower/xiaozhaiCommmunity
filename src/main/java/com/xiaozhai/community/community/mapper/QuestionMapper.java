package com.xiaozhai.community.community.mapper;

import com.xiaozhai.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title, description, gmt_create, gmt_modified, tag, creator) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{tag}, #{creator})")
    void create(Question question);

    @Select("select * from question ")
    List<Question> questionWholeList();

    @Select("select * from question limit #{size} offset #{offset}")
    List<Question> questionByPage(int offset, int size);

    @Select("select * from question where creator = #{id}limit #{size} offset #{offset}")
    List<Question> questionByPageAndId(Integer id, Integer offset, int size);

    @Select("select count(id) from question")
    Integer countQuestion();

    @Select("select count(id)  from question where creator = #{id}")
    Integer countQuestionById(Integer id);
}
