package com.xiaozhai.community.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    SYS_ERROR(500, "服务器冒烟了，要不歇会儿再来"),
    TARGET_NOT_SELECTED(2001,"未选中问题或评论"),
    USER_NOT_LOGIN(2002,"用户未登录,请先登录"),
    COMMENT_TYPE_ILLEGAL(2003,"评论类型错误")
    ;

    private String message;
    private int code;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCOde() {
        return code;
    }

    CustomizeErrorCode(int code, String message) {
        this.message = message;
        this.code = code;
    }
}
