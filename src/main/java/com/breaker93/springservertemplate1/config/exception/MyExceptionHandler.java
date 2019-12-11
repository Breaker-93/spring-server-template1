package com.breaker93.springservertemplate1.config.exception;

import com.breaker93.springservertemplate1.utils.Result.ResponseResult;
import com.breaker93.springservertemplate1.utils.Result.ResultEnums;
import com.breaker93.springservertemplate1.utils.Result.ResultUtil;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseResult handle(Exception e) {
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return ResultUtil.build(ResultEnums.NOT_FIND, e.getMessage());
        }
        e.printStackTrace();
        return ResultUtil.build(ResultEnums.SYSTEM_EXCEPTION, e.toString());
    }

}
