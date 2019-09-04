package com.breaker93.springservertemplate1.utils.Result;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public ResponseResult() {
    }
    public ResponseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResponseResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public ResponseResult(ResultEnums resultEnums) {
        this.code = resultEnums.getCode();
        this.msg = resultEnums.getMsg();
    }
    public ResponseResult(ResultEnums resultEnums, T data) {
        this.code = resultEnums.getCode();
        this.msg = resultEnums.getMsg();
        this.data = data;
    }
}
