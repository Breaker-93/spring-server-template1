package com.breaker93.springservertemplate1.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.breaker93.springservertemplate1.sys.entity.SysUser;
import com.breaker93.springservertemplate1.sys.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<SysUser> queryWrapper = Wrappers.<SysUser>query()
                .eq("username", s);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
//        SysUser sysUser = new SysUser("admin", new BCryptPasswordEncoder().encode("123456789"));
//        判断用户名不存在情况需自定义异常、才能被过滤器单独捕获到（框架里自带的会被处理成BadCredentialsException抛出）
//        if(null == sysUser){
//            throw new UsernameNotFoundException(String.format("'%s'该登录名不存在.",s));
//        }
        return sysUser;
    }
}
