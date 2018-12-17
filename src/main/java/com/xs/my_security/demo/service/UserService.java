package com.xs.my_security.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xs.my_security.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 谢霜
 * @since 2018-12-01
 */
public interface UserService extends IService<User> {
    IPage page(IPage page, User user);
}
