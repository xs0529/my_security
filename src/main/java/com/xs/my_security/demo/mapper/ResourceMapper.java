package com.xs.my_security.demo.mapper;

import com.xs.my_security.demo.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 谢霜
 * @since 2018-12-01
 */
public interface ResourceMapper extends BaseMapper<Resource> {
    List<Resource> getResourceByUserId(@Param("userId") Integer userId);
}
