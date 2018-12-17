package com.xs.my_security.demo.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xs.my_security.demo.entity.Resource;
import com.xs.my_security.demo.entity.UserRole;
import com.xs.my_security.demo.mapper.ResourceMapper;
import com.xs.my_security.demo.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xs.my_security.demo.service.RoleService;
import com.xs.my_security.demo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 谢霜
 * @since 2018-12-01
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<Resource> getResourceByUserId(Integer userId) {
        return resourceMapper.getResourceByUserId(userId);
    }
}
