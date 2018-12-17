package com.xs.my_security.demo.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.xs.my_security.demo.common.bean.ResponseResult;
import com.xs.my_security.demo.common.config.redis.RedisKeys;
import com.xs.my_security.demo.common.config.redis.RedisUtils;
import com.xs.my_security.demo.entity.Resource;
import com.xs.my_security.demo.entity.User;
import com.xs.my_security.demo.service.LoginService;
import com.xs.my_security.demo.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-17
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String login(User user) {
        User user1 = new User();
        user1.setId(user.getId());
        user1.setLastLoginTime(new Date());
        if (!user1.updateById()){
            return null;
        }
        List<Resource> resourceByUserId = resourceService.getResourceByUserId(user.getId());
        Map<String, String> map = new HashMap();
        resourceByUserId.forEach(resource -> {
            if (resource!=null){
                map.put(resource.getUrl(),resource.getUrl());
            }
        });
        redisUtils.set(RedisKeys.getUserResourceKey(user.getUsername()), map,7200);
        String s = UUID.randomUUID().toString();
        String token = DigestUtil.md5Hex(s+"token");
        user.setLastLoginTime(new Date());
        redisUtils.set(RedisKeys.getTokenKey(token),user,7200);
        String oldToken = (String) redisUtils.get(RedisKeys.getUserTokenKey(user.getUsername()));
        if (StrUtil.isNotBlank(oldToken)){
            redisUtils.del(RedisKeys.getTokenKey(oldToken));
        }
        redisUtils.set(RedisKeys.getUserTokenKey(user.getUsername()),token,7200);
        return token;
    }
}
