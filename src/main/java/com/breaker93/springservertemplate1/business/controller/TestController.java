package com.breaker93.springservertemplate1.business.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breaker93.springservertemplate1.sys.entity.SysRole;
import com.breaker93.springservertemplate1.sys.mapper.SysRoleMapper;
import com.breaker93.springservertemplate1.utils.Result.ResponseResult;
import com.breaker93.springservertemplate1.utils.Result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    SysRoleMapper sysRoleMapper;
    @GetMapping
    public String get(HttpServletRequest request){
        System.out.println(request.getUserPrincipal());
        System.out.println(request.isUserInRole("ROLE_USER"));
        if (true) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return "Hello,Tianjin!";
    }

    @PostMapping
    public String post(String name){
        return "Welcome to " + name;
    }

    @PostMapping("/role")
    public String postRole(SysRole sysRole) {
        int res = sysRoleMapper.insert(sysRole);
        return res > 0 ? "操作成功":"操作失败";
    }

    @PutMapping("/role")
    public String putRole(SysRole sysRole){
        QueryWrapper<SysRole> queryWrapper =  Wrappers.<SysRole>query()
                .eq("business_id", sysRole.getBusinessId());
        sysRole.setBusinessId(null);
        int res = sysRoleMapper.update(sysRole, queryWrapper);
        return res > 0 ? "操作成功":"操作失败";
    }

    @GetMapping("/role")
    public ResponseResult getRole(int page,int size, String content){
        Page<SysRole> pageObj = new Page<>(page,size);
        pageObj.addOrder(OrderItem.desc("create_time"));
        pageObj.setSearchCount(false);
        QueryWrapper<SysRole> queryWrapper =  Wrappers.<SysRole>query()
                .like("role_name", content)
                .or()
                .like("role_code",content);
        IPage<SysRole> list = sysRoleMapper.selectPage(pageObj,content == null ? null : queryWrapper);
        return ResultUtil.buildSuccess(list);

    }
}
