package com.xiaozhai.community.community.enums;

import lombok.Data;

public enum CommentType {
    QUESTION(0),
    COMMENT(1)
    ;

    Integer type;

    CommentType(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type){
        return type.equals(1)||type.equals(0);
    }

    public Integer getType(){return this.type;}

}
