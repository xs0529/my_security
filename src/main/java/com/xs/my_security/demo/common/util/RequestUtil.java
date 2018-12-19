package com.xs.my_security.demo.common.util;

import com.xs.my_security.demo.entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 全局获取用户信息
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-17
 */
public class RequestUtil {

    /**
     * 全局获取用户信息
     * @return
     */
    public static User getCurrentUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User)request.getAttribute("user");
        return user;
    }
}
