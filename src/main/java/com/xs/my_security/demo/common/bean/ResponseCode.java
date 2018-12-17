package com.xs.my_security.demo.common.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/18/10:54
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseCode {

    OK(200,"操作成功"),
    SIGN_IN_OK(2,"登录成功"),
    LOGOUT_OK(3,"注销登录成功"),
    SIGN_IN_INPUT_FAIL(-4,"账号或密码错误"),
    SIGN_IN_FAIL(-3,"登录失败"),
    FAIL(-1,"操作失败"),
    LOGOUT_FAIL(-2,"注销登录失败"),
    SING_IN_INPUT_EMPTY(-5,"账户和密码均不能为空"),
    NOT_SING_IN(-6,"用户未登录或身份异常"),
    ERROR404(404,"访问路径未找到"),
    ERROR500(500,"服务器错误"),
    OTHER(-1,"未知错误"),
    NON_AUTHORITATIVE_INFORMATION(203, "非授权信息"),
    NO_CONTENT(204, "无内容"),
    RESET_CONTENT(205, "重置内容"),
    PARTIAL_CONTENT(206, "部分内容"),
    MULTI_STATUS(207, "多状态"),
    ALREADY_REPORTED(208, "已经报道"),
    IM_USED(226, "我用过"),
    MULTIPLE_CHOICES(300, "多种选择"),
    MOVED_PERMANENTLY(301, "永久移除"),
    FOUND(302, "发现"),
    /** @deprecated */
    @Deprecated
    MOVED_TEMPORARILY(302, "临时移动"),
    SEE_OTHER(303, "看到其他"),
    NOT_MODIFIED(304, "未修改"),
    /** @deprecated */
    @Deprecated
    USE_PROXY(305, "使用代理"),
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    PERMANENT_REDIRECT(308, "Permanent Redirect"),
    BAD_REQUEST(400, "客户端请求错误"),
    UNAUTHORIZED(401, "Unauthorized"),
    PAYMENT_REQUIRED(402, "Payment Required"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    REQUEST_TIMEOUT(408, "Request Timeout"),
    CONFLICT(409, "Conflict"),
    GONE(410, "Gone"),
    LENGTH_REQUIRED(411, "Length Required"),
    PRECONDITION_FAILED(412, "Precondition Failed"),
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    /** @deprecated */
    @Deprecated
    REQUEST_ENTITY_TOO_LARGE(413, "Request Entity Too Large"),
    URI_TOO_LONG(414, "URI Too Long"),
    /** @deprecated */
    @Deprecated
    REQUEST_URI_TOO_LONG(414, "Request-URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),
    EXPECTATION_FAILED(417, "Expectation Failed"),
    I_AM_A_TEAPOT(418, "I'm a teapot"),
    /** @deprecated */
    @Deprecated
    INSUFFICIENT_SPACE_ON_RESOURCE(419, "Insufficient Space On Resource"),
    /** @deprecated */
    @Deprecated
    METHOD_FAILURE(420, "Method Failure"),
    /** @deprecated */
    @Deprecated
    DESTINATION_LOCKED(421, "Destination Locked"),
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    LOCKED(423, "Locked"),
    FAILED_DEPENDENCY(424, "失败的依赖"),
    UPGRADE_REQUIRED(426, "需要升级"),
    PRECONDITION_REQUIRED(428, "前提要求"),
    TOO_MANY_REQUESTS(429, "请求太多"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "请求标头字段太大"),
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "法律原因不可用"),
    INTERNAL_SERVER_ERROR(500, "内部服务器错误"),
    NOT_IMPLEMENTED(501, "未实现"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "暂停服务"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    HTTP_VERSION_NOT_SUPPORTED(505, "不支持HTTP版本"),
    INSUFFICIENT_STORAGE(507, "存储空间不足"),
    LOOP_DETECTED(508, "检测到环路"),
    BANDWIDTH_LIMIT_EXCEEDED(509, "超出带宽限制"),
    NOT_EXTENDED(510, "没有扩展"),
    NETWORK_AUTHENTICATION_REQUIRED(511, "需要网络验证");
    public Integer code;

    public String msg;

    public static List<ResponseMessage> getArrayMessage(){
        ArrayList<ResponseMessage> responseMessages = new ArrayList<>();
        for (ResponseCode statusEnum : ResponseCode.values()) {
            responseMessages.add(new ResponseMessageBuilder()
                    .code(statusEnum.code)
                    .message(statusEnum.msg)
                    .build());
        }
        return responseMessages;
    }

}
