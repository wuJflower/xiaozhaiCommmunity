package com.xiaozhai.community.community.dto;

import com.xiaozhai.community.community.exception.CustomizedException;
import lombok.Data;

import java.util.List;


//定义泛型类
@Data
public class ResultDTO<T> {

     private int code;
     private String massage;
     private T data;

    public ResultDTO() {
    }

    public ResultDTO(int code, String massage) {
        this.code = code;
        this.massage = massage;
    }

    public  ResultDTO okOf() {
        return new ResultDTO(200, "评论添加成功");
    }

    public  ResultDTO errorOf(CustomizedException ex) {
        return new ResultDTO(ex.getCode(), ex.getMessage());
    }


    public static <T> ResultDTO okOf(T t) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMassage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }
}
