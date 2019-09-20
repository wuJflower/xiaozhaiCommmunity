package com.xiaozhai.community.community.enums;

public enum CommentType {
    QUESTION(0),
    COMMENT(1)
    ;

    Integer type;

    CommentType(Integer type) {
        this.type = type;
    }

    public static boolean isExist(Integer type){
        return type.equals(COMMENT)&&type.equals(QUESTION);
    }

}
