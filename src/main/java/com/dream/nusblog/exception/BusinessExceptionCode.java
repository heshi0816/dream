package com.dream.nusblog.exception;

public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("user name exist"),
    LOGIN_USER_ERROR("user name doesn't exist or wrong password"),
    VOTE_REPEAT("you have already liked this doc"),
    Null_Pointer("null pointer"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
