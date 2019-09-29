package com.xiaozhai.community.community.service;

import com.xiaozhai.community.community.dto.CommentDTO;
import com.xiaozhai.community.community.dto.ResultDTO;
import com.xiaozhai.community.community.enums.CommentType;
import com.xiaozhai.community.community.exception.CustomizeErrorCode;
import com.xiaozhai.community.community.exception.CustomizedException;
import com.xiaozhai.community.community.mapper.CommentMapper;
import com.xiaozhai.community.community.mapper.QuestionEtxMapper;
<<<<<<< HEAD
import com.xiaozhai.community.community.mapper.UserMapper;
import com.xiaozhai.community.community.model.*;
import org.springframework.beans.BeanUtils;
=======
import com.xiaozhai.community.community.model.Comment;
import com.xiaozhai.community.community.model.Question;
import com.xiaozhai.community.community.model.User;
>>>>>>> e77cf45f42657b7f1b98800bd368630c457da6d7
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

=======
>>>>>>> e77cf45f42657b7f1b98800bd368630c457da6d7
@Transactional //注解实现事务
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionEtxMapper questionEtxMapper;

    @Autowired
    private UserMapper userMapper;

    public void insert(Comment comment, User user) {
//      选择的问题或者评论不存在
        if (comment.getParentId() == null) {
            throw new CustomizedException(CustomizeErrorCode.TARGET_NOT_SELECTED);
        }
//        评论类型非法
        if (comment.getType() == null || !CommentType.isExist(comment.getType())) {
            throw new CustomizedException(CustomizeErrorCode.COMMENT_TYPE_ILLEGAL);
        }

//        写入评论人
        comment.setCommentator(user.getId());
//     选择性插入,否则会用null覆盖表中初始的likecount
        commentMapper.insertSelective(comment);
        //问题评论成功后评论数自加一
        Question record = new Question();
        record.setId(comment.getParentId());
        questionEtxMapper.incCommentCount(record);
    }

    public List<CommentDTO> listById(int id, CommentType type) {
        CommentExample example = new CommentExample();
//        将查询需要用的字段写入Example类中
        example.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
//        输入参数是排序的约束字段,
        example.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(example);
//        如果评论数为0,直接返回空表
        if (comments.size() == 0) {
            return new ArrayList<>();
        }

//        评论者去重
        Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Integer> usersId = new ArrayList();
        usersId.addAll(commentators);
//      获取评论人并转为Map
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(usersId);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        List<CommentDTO> commentDTOList = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setCommentator(12);
            //            查询结果转存到commentDTO
            BeanUtils.copyProperties(comment,commentDTO);
//            取出评论者
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOList;
    }
}
