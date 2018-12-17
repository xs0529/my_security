package com.xs.my_security.demo.controller;


import com.xs.my_security.demo.common.bean.ResponseResult;
import com.xs.my_security.demo.common.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 谢霜
 * @since 2018-12-01
 */
@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @GetMapping("list")
    public ResponseResult list(){
        log.info(RequestUtil.getCurrentUser().toString());
        return ResponseResult.ok("获取所有角色成功");
    }

    @GetMapping("get")
    public ResponseResult get(){
        return ResponseResult.ok("获取角色成功");
    }

    @GetMapping("del")
    public ResponseResult del(){
        return ResponseResult.ok("删除角色成功");
    }

    @PostMapping("add")
    public ResponseResult add(){
        return ResponseResult.ok("添加角色成功");
    }

    @PostMapping("update")
    public ResponseResult update(){
        return ResponseResult.ok("更新角色成功");
    }
}

