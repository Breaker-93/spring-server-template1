package com.breaker93.springservertemplate1.config.filter;

import com.alibaba.fastjson.JSON;
import com.breaker93.springservertemplate1.utils.Result.ResultEnums;
import com.breaker93.springservertemplate1.utils.Result.ResultUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class JwtFilter extends GenericFilterBean {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenPrefix}")
    private String tokenPrefix;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        try {
            String jwtToken = req.getHeader(tokenHeader);
            if(jwtToken == null || jwtToken.indexOf(tokenPrefix) == -1) {
                throw new SignatureException("");
            }
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken.replace(tokenPrefix, ""))
                    .getBody();
            // 获取当前登录用户名
            String username = claims.getSubject();
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(token);
            filterChain.doFilter(req,servletResponse);
        }catch (Exception e) {
           // System.out.println(e);
            String resJson;
            if (e instanceof SignatureException || e instanceof MalformedJwtException) {
                // 认证失败（未携带令牌或令牌签名错误）
                resJson = JSON.toJSONString(ResultUtil.build(ResultEnums.UNAUTHORIZED));
            } else {
                // 登录认证已过期
                resJson = JSON.toJSONString(ResultUtil.build(ResultEnums.AUTHORIZE_EXPIRE));
            }
            servletResponse.setContentType("application/json;charset=utf-8");
            PrintWriter out = servletResponse.getWriter();
            out.write(resJson);
            out.flush();
            out.close();
        }
    }
}
