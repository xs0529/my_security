package com.xs.my_security.demo.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author licoy.cn
 * @version 2017/11/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "请求结果响应体")
public class ResponseResult<T> implements Serializable {


    @ApiModelProperty(value = "响应状态回执码")
    private Integer status;

    @ApiModelProperty(value = "数据体")
    private T data;

    @ApiModelProperty(value = "响应回执消息")
    private String msg;

    @ApiModelProperty(value = "响应时间戳")
    private final long timestamps = System.currentTimeMillis();

    public synchronized static <T> ResponseResult<T> e(ResponseCode statusEnum) {
        return e(statusEnum,null);
    }

    public synchronized static <T> ResponseResult<T> e(ResponseCode statusEnum, T data) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setStatus(statusEnum.code);
        res.setMsg(statusEnum.msg);
        res.setData(data);
        return res;
    }

    public  static <T> ResponseResult<T> ok(String msg,T data) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setStatus(ResponseCode.OK.code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    public  static <T> ResponseResult<T> ok(T data) {
        return ok(ResponseCode.OK.msg, data);
    }

    public  static <T> ResponseResult<T> ok(String msg) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setStatus(ResponseCode.OK.code);
        res.setMsg(msg);
        return res;
    }

    public  static <T> ResponseResult<T> ok() {
        return ok(ResponseCode.OK.msg);
    }

    public  static <T> ResponseResult<T> fail(String msg,T data) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setStatus(ResponseCode.FAIL.code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    public  static <T> ResponseResult<T> fail(T data) {
        return ok(ResponseCode.FAIL.msg, data);
    }

    public  static <T> ResponseResult<T> fail(String msg) {
        ResponseResult<T> res = new ResponseResult<>();
        res.setStatus(ResponseCode.FAIL.code);
        res.setMsg(msg);
        return res;
    }

    public  static <T> ResponseResult<T> fail() {
        return ok(ResponseCode.FAIL.msg);
    }



    private static final long serialVersionUID = 8992436576262574064L;
}
