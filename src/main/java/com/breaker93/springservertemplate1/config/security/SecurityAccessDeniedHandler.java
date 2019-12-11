package com.breaker93.springservertemplate1.config.security;

import com.alibaba.fastjson.JSON;
import com.breaker93.springservertemplate1.utils.Result.ResultEnums;
import com.breaker93.springservertemplate1.utils.Result.ResultUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description: This is a handler class for securiy access denied.
 * @author: Breaker93
 * @createTime: 2019/9/16 20:15
 */
public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        e.printStackTrace();
        String resJson = JSON.toJSONString(ResultUtil.build(ResultEnums.PERMISSION_DENIED));
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(resJson);
        out.flush();
        out.close();
    }
}
