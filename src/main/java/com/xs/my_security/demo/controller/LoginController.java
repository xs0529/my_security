package com.xs.my_security.demo.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xs.my_security.demo.bean.dto.LoginDTO;
import com.xs.my_security.demo.common.bean.ResponseResult;
import com.xs.my_security.demo.common.config.redis.RedisKeys;
import com.xs.my_security.demo.common.config.redis.RedisUtils;
import com.xs.my_security.demo.common.util.TimeUtil;
import com.xs.my_security.demo.common.util.ValidUtil;
import com.xs.my_security.demo.entity.User;
import com.xs.my_security.demo.service.LoginService;
import com.xs.my_security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-01
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;
    @Value("${yanzhi}")
    private String YANZHI;

    @PostMapping
    public ResponseResult login(@Valid LoginDTO loginDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return ValidUtil.VaildMessage(bindingResult);
        }
        long expire = redisUtils.getExpire(RedisKeys.getUserLoginErrorTimeKey(loginDTO.getUsername()));
        if (expire>0){
            return ResponseResult.fail("已超出登录失败次数限制，请在"+ TimeUtil.second2Chinese(expire)+"后重试！");
        }
        User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, loginDTO.getUsername()));
        if (user==null){
            return ResponseResult.fail("用户名错误");
        }
        if (user.getIsUsable().equals(0)){
            return ResponseResult.fail("账号已被锁定");
        }
        if (!user.getPassword().equals(DigestUtil.md5Hex(DigestUtil.md5Hex(loginDTO.getPassword())+YANZHI))){
            String redisKey1 = RedisKeys.getUserLoginErrorCountKey(user.getUsername());
            if (redisUtils.get(redisKey1)==null){
                redisUtils.set(redisKey1,1,60);
            }else {
                redisUtils.incr(redisKey1,1);
            }
            int count = (int)redisUtils.get(redisKey1);
            if (count>=5){
                redisUtils.set(RedisKeys.getUserLoginErrorTimeKey(user.getUsername()),300,300);
                return ResponseResult.fail("已超出登录失败次数限制，请在"+ TimeUtil.second2Chinese(300)+"后重试！");
            }else {
                return ResponseResult.fail("密码错误,您还有 "+(5-count)+" 次机会！");
            }
        }
        String token = loginService.login(user);
        if (StrUtil.isNotBlank(token)){
            return ResponseResult.ok("登录成功",token);
        }else {
            return ResponseResult.fail("登录失败");
        }
    }
}
