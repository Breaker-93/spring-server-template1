package com.breaker93.springservertemplate1.config.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.breaker93.springservertemplate1.sys.entity.SysUser;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @description: This is a handler class for meta object.
 * @author: Breaker93
 * @createTime: 2019/9/21 11:08
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.setFieldValByName("businessId",  UUID.randomUUID().toString().replaceAll("-", ""), metaObject);
        this.setFieldValByName("createBy", sysUser.getBusinessId(), metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", sysUser.getBusinessId(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        SysUser sysUser = (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.setFieldValByName("updateBy", sysUser.getBusinessId(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}
