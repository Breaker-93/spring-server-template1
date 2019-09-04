package com.breaker93.springservertemplate1.utils.Result;

public class ResultUtil {

    public static ResponseResult buildSuccess(){
        return new ResponseResult(ResultEnums.SUCCESS);
    }

    public static ResponseResult buildSuccess(String msg){
        return new ResponseResult(ResultEnums.SUCCESS.getCode(), msg);
    }

    public static <T> ResponseResult buildSuccess(T data){
        return new ResponseResult<T>(ResultEnums.SUCCESS, data);
    }

    public static ResponseResult buildError(){
        return new ResponseResult(ResultEnums.ERROR);
    }

    public static ResponseResult buildError(String msg){
        return new ResponseResult(ResultEnums.ERROR.getCode(), msg);
    }

    public static <T> ResponseResult buildError(T data){
        return new ResponseResult<T>(ResultEnums.ERROR, data);
    }


    public static ResponseResult build(String code, String msg){
        return new ResponseResult(code, msg);
    }

    public static <T> ResponseResult build(String code, String msg, T data){
        return new ResponseResult(code, msg, data);
    }

    public static <T> ResponseResult build(ResultEnums enums){
        return new ResponseResult(enums);
    }

    public static <T> ResponseResult build(ResultEnums enums,T data){
        return new ResponseResult(enums, data);
    }
}
