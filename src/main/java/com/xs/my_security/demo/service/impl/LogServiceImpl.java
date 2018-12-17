package com.xs.my_security.demo.service.impl;

import com.xs.my_security.demo.entity.Log;
import com.xs.my_security.demo.mapper.LogMapper;
import com.xs.my_security.demo.service.LogService;
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
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
