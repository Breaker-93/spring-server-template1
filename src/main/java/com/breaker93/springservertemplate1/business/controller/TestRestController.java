package com.breaker93.springservertemplate1.business.controller;

import com.breaker93.springservertemplate1.utils.Result.ResponseResult;
import com.breaker93.springservertemplate1.utils.Result.ResultUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @description: This is a test controller class for restful api.
 * @author: Breaker93
 * @createTime: 2019/9/21 17:25
 */
@RestController
@RequestMapping("/books")
public class TestRestController {
    @GetMapping
    public ResponseResult get() {
        return ResultUtil.buildSuccess("GET/ 查询成功(获取所有数据)");
    }
    @GetMapping("{id}")
    public ResponseResult get(@PathVariable("id") String id) {
        return ResultUtil.buildSuccess("GET/ 查询成功(根据id获取详情)" + id);
    }

    @PostMapping
    public ResponseResult post() {
        return ResultUtil.buildSuccess("POST/ 添加成功");
    }

    @PutMapping
    public ResponseResult put() {
        return ResultUtil.buildSuccess("PUT/ 修改成功");
    }

    @DeleteMapping
    public ResponseResult delete() {
        return ResultUtil.buildSuccess("DELETE/ 删除成功");
    }

}
