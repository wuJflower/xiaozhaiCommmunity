package com.xiaozhai.community.community.dto;

import com.xiaozhai.community.community.exception.CustomizedException;
import lombok.Data;

@Data
public class ResultDTO {

     private int code;
     private String massage;

    public ResultDTO() {
    }

    public ResultDTO(int code, String massage) {
        this.code = code;
        this.massage = massage;
    }

    public  ResultDTO okOf() {
        return new ResultDTO(code, massage);
    }

    public  ResultDTO errorOf(CustomizedException ex) {
        return new ResultDTO(ex.getCode(), ex.getMessage());
    }


}
