package com.breaker93.springservertemplate1.sys.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @description: This is a entity class for table sys_role.
 * @author: Breaker93
 * @createTime: 2019/9/21 10:57
 */
@Data
public class SysRole {
    @TableField(fill = FieldFill.INSERT)
    private String businessId;
    private String roleName;
    private String roleCode;

    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
