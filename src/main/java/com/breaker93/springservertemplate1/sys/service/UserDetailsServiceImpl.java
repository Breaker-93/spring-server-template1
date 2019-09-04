package com.breaker93.springservertemplate1.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.breaker93.springservertemplate1.sys.entity.User;
import com.breaker93.springservertemplate1.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = Wrappers.<User>query()
                .eq("username", s);
        User user = userMapper.selectOne(queryWrapper);
//        User user = new User("admin", new BCryptPasswordEncoder().encode("123456789"));
//        判断用户名不存在情况需自定义异常、才能被过滤器单独捕获到（框架里自带的会被处理成BadCredentialsException抛出）
//        if(null == user){
//            throw new UsernameNotFoundException(String.format("'%s'该登录名不存在.",s));
//        }
        return user;
    }
}
