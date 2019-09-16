package com.breaker93.springservertemplate1.config.security;

import com.breaker93.springservertemplate1.config.filter.JwtFilter;
import com.breaker93.springservertemplate1.config.filter.JwtLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    // 通过@Bean注入配置文件中的参数
    @Bean
    JwtLoginFilter getJwtLoginFilter() throws Exception {
        return new JwtLoginFilter("/login", authenticationManager());
    }
    // 通过@Bean注入配置文件中的参数
    @Bean
    JwtFilter getJwtFilter(){
        return new JwtFilter();
    }

    @Bean
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new SecurityAccessDeniedHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/abc/**").hasAuthority("user")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  //禁用session
                .and()
                .formLogin() // 被拦截的请求会自动跳转至默认的登录页面
                .and()
                .exceptionHandling().accessDeniedHandler(getAccessDeniedHandler())
                .and()
                .addFilterBefore(getJwtLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(getJwtFilter(),UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
