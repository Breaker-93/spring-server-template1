package com.breaker93.springservertemplate1.utils.Result;

public enum  ResultEnums {
    SUCCESS("200", "操作成功"),
    LOGIN_SUCCESS("200","登录成功"),
    ERROR("400", "操作失败"),
    LOGIN_ERROR("401","登录失败"),
    UNAUTHORIZED("402", "认证失败（未携带令牌或令牌签名错误）"),
    AUTHORIZE_EXPIRE("403", "登录认证已过期"),
    NOT_FIND("404", "接口找不到"),
    PERMISSION_DENIED("405", "权限不足"),
    BUSINESS_ERROR("500", "业务逻辑错误"),
    SYSTEM_EXCEPTION("501", "系统未知异常");

    private String code;
    private String msg;

    ResultEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
