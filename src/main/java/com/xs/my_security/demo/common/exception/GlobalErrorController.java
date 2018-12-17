package com.xs.my_security.demo.common.exception;

import com.xs.my_security.demo.common.bean.ResponseCode;
import com.xs.my_security.demo.common.bean.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 全局异常信息处理类
 * 原文：https://www.jianshu.com/p/3998ea8b53a8
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-07
 */
@Slf4j
@Controller
@RequestMapping("${server.error.path:/error}")
public class GlobalErrorController implements ErrorController {

    @Autowired
    private ErrorInfoBuilder errorInfoBuilder;//错误信息的构建工具.

    /**
     * 返回详细的错误信息(JSON).
     */
    @RequestMapping
    @ResponseBody
    public ResponseResult error(HttpServletRequest request) {
        ErrorInfo errorInfo = errorInfoBuilder.getErrorInfo(request);
        log.error(errorInfo.getStatus()+":"+errorInfo.getError());
        switch (errorInfo.getStatus()){
            case 400:
                return ResponseResult.e(ResponseCode.BAD_REQUEST);
            case 404:
                return ResponseResult.e(ResponseCode.ERROR404);
            case 500:
                return ResponseResult.e(ResponseCode.ERROR500);
            case 405:
                return ResponseResult.e(ResponseCode.METHOD_NOT_ALLOWED);
            default:
                return ResponseResult.e(ResponseCode.OTHER);
        }
    }

    @Override
    public String getErrorPath() {//获取映射路径
        return errorInfoBuilder.getErrorProperties().getPath();
    }
}