package com.github.gun2.beadalbujok.constant;

public enum ErrorCode implements ResponseCode{

    INVALID_INPUT_VALUE(400, "INVALID_INPUT_VALUE", "Invalid Input Value"),
    USERNAME_ALREADY_EXIST(400, "USERNAME_ALREADY_EXIST", "Username is already exist"),
    INVALID_TYPE_VALUE(400,"INVALID_TYPE_VALUE", " Invalid Type Value"),
    HANDLE_ACCESS_DENIED(403 ,"HANDLE_ACCESS_DENIED", "Access is Denied"),
    FORBIDDEN(403 ,"FORBIDDEN", "Access forbidden"),
    METHOD_NOT_ALLOWED(405,"METHOD_NOT_ALLOWED", "Invalid Input Value"),
    NOT_ENOUGH_POINT(400, "NOT_ENOUGH_POINT", "Not enough point"),
    NOT_FOUND_MEMBER(400, "NOT_FOUND_MEMBER", "Not found member"),
    NOT_FOUND_GIFTICON(400, "NOT_FOUND_GIFTICON", "Not found gifticon"),
    ALREADY_USED_GIFTICON(400, "ALREADY_USED_GIFTICON", "Already used gifticon"),
    IMPOSSIBLE_UPDATE_SELF(400, "IMPOSSIBLE_UPDATE_SELF", "Impossible update self"),
    NOT_EQUALS_MEMBER_PASSWORD(401, "NOT_EQUALS_MEMBER_PASSWORD", "Not equals member password"),
    CONTENT_TYPE_NOT_SUPPORTED(406,"CONTENT_TYPE_NOT_SUPPORTED", "Content Type Not Supported"),
    INTERNAL_SERVER_ERROR(500,"INTERNAL_SERVER_ERROR", "Server Error");

    private int status;
    private String code;
    private String message;

    ErrorCode(int status, String code, String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }


    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
