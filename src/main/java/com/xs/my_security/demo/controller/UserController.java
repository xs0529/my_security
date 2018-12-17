package com.xs.my_security.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xs.my_security.demo.common.bean.ResponseResult;
import com.xs.my_security.demo.common.util.RequestUtil;
import com.xs.my_security.demo.common.util.ValidUtil;
import com.xs.my_security.demo.entity.User;
import com.xs.my_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 谢霜
 * @since 2018-12-01
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public ResponseResult list(@RequestParam(defaultValue = "0",value = "page") int page
            , @RequestParam(defaultValue = "10",value = "size") int size
            , User user){
        IPage iPage = new Page(page,size);
        IPage data = userService.page(iPage, user);
        return ResponseResult.ok("获取所有用户信息成功",data);
    }

    @GetMapping("get")
    public ResponseResult get(@RequestParam(defaultValue = "0", value = "id")int id){
        User user = userService.getById(id);
        if (user==null){
            return ResponseResult.fail("获取用户信息失败");
        }else {
            return ResponseResult.ok("获取用户信息成功",user );
        }
    }

    @GetMapping("del")
    public ResponseResult del(@RequestParam(defaultValue = "[]",value = "ids")Integer [] ids){
        if (ids.length<=0){
            return ResponseResult.fail("请传入要删除用户的id");
        }
        List<Integer> ints = Arrays.asList(ids);
        if (ints.contains(RequestUtil.getCurrentUser().getId())){
            return ResponseResult.fail("不能删除自己");
        }
        if (userService.removeByIds(ints)){
            return ResponseResult.ok("删除用户信息成功");
        }else {
            return ResponseResult.fail("删除用户信息失败");
        }
    }

    @PostMapping("add")
    public ResponseResult add(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ValidUtil.VaildMessage(bindingResult);
        }
        if (userService.save(user)){
            return ResponseResult.ok("添加用户信息成功");
        }else {
            return ResponseResult.fail("添加用户信息失败");
        }
    }

    @PostMapping("update")
    public ResponseResult update(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ValidUtil.VaildMessage(bindingResult);
        }
        if (user.getId()==null){
            return ResponseResult.fail("请传入要更新用户的id");
        }
        if (userService.updateById(user)){
            return ResponseResult.ok("更新用户信息成功");
        }else {
            return ResponseResult.fail("更新用户信息失败");
        }
    }
}

