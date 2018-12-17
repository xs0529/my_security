package com.xs.my_security.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xs.my_security.demo.entity.User;
import com.xs.my_security.demo.mapper.UserMapper;
import com.xs.my_security.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2018-12-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public IPage page(IPage page, User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        if (user.getIsUsable()!=null){
            queryWrapper.eq(User::getIsUsable,user.getIsUsable());
        }
        if (user.getUsername()!=null){
            queryWrapper.like(User::getUsername,user.getUsername());
        }
        return page(page,queryWrapper);
    }
}
