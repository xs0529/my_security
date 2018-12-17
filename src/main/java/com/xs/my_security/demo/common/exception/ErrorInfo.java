package com.xs.my_security.demo.common.exception;

import lombok.Data;

/**
 * <p>
 *  异常信息对象
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-07
 */
@Data
public class ErrorInfo {

    private String timestamps;//发生时间
    private String url;//访问Url
    private String error;//错误类型
    String stackTrace;//错误的堆栈轨迹
    private int status;//状态码
    private String reasonPhrase;//状态码
}
