package com.xs.my_security.demo.common.util;

import com.xs.my_security.demo.common.bean.ResponseResult;
import org.springframework.validation.BindingResult;

/**
 * <p>
 * 统一参数校验工具类
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-17
 */
public class ValidUtil {

    public synchronized static ResponseResult VaildMessage(BindingResult bindingResult){
        StringBuffer stringBuffer = new StringBuffer();
        bindingResult.getAllErrors().forEach(objectError -> {
            stringBuffer.append(objectError.getDefaultMessage()).append(",");
        });
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return ResponseResult.fail(stringBuffer.toString());
    }
}
