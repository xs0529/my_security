package com.xs.my_security.demo.service;

import com.xs.my_security.demo.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 谢霜
 * @since 2018-12-01
 */
public interface ResourceService extends IService<Resource> {
    List<Resource> getResourceByUserId(Integer userId);
}
