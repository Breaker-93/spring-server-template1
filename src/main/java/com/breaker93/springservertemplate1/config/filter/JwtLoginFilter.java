package com.breaker93.springservertemplate1.config.filter;

import com.alibaba.fastjson.JSON;
import com.breaker93.springservertemplate1.utils.Result.ResultEnums;
import com.breaker93.springservertemplate1.utils.Result.ResultUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken( (String)httpServletRequest.getParameter("username"),  (String)httpServletRequest.getParameter("password")));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        StringBuffer as = new StringBuffer();
        for(GrantedAuthority authority: authorities) {
            as.append(authority.getAuthority()).append(",");
        }
        String jwt = Jwts.builder()
                .claim("authorities", as)//配置用户角色
                .setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
        response.setContentType("application/json;charset=utf-8");
//        response.setHeader("token", jwt);
        PrintWriter out = response.getWriter();
//        out.write(new ObjectMapper().writeValueAsString(jwt));
        Map<String,String> resultMap = new HashMap<>();
        resultMap.put(tokenHeader, jwt);
        String resJson = JSON.toJSONString(ResultUtil.build(ResultEnums.LOGIN_SUCCESS,resultMap));
        out.write(resJson);
        out.flush();
        out.close();
    }

    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse resp, AuthenticationException failed) throws IOException, ServletException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String resJson = JSON.toJSONString(ResultUtil.build(ResultEnums.LOGIN_ERROR,"用户名与密码不匹配"));
        out.write(resJson);
        out.flush();
        out.close();
    }
}

