package com.xs.my_security.demo.common.config.security;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.xs.my_security.demo.common.config.redis.RedisKeys;
import com.xs.my_security.demo.common.config.redis.RedisUtils;
import com.xs.my_security.demo.entity.Resource;
import com.xs.my_security.demo.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  启动时加载所有的权限
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-01
 */
@Slf4j
@Component
public class MyResourceConfig {

    @Autowired
    public MyResourceConfig(ResourceService resourceService,RedisUtils redisUtils){
        List<Resource> resourceList = resourceService.list();
        if (CollectionUtil.isNotEmpty(resourceList)){
            for (Resource resource : resourceList){
                if (StrUtil.isNotBlank(resource.getUrl())){
                    if (StrUtil.isNotBlank(resource.getPermission())){
                        try {
                            redisUtils.set(RedisKeys.getResourceKey(resource.getUrl()),resource);
                        }catch (Exception e){
                            log.error("添加权限到redis失败");
                        }

                    }
                }
            }
        }
    }
}
