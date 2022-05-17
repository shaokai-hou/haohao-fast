package com.haohao.fast.common.result;

import lombok.Getter;

/**
 * 响应枚举类
 *
 * @author haohao
 */
@Getter
public enum ResultCodeEnum {

    /**
     * SUCCESS
     */
    SUCCESS(200, "成功"),
    /**
     * PARAM_ERROR
     */
    PARAM_ERROR(201, "参数错误"),
    /**
     * NULL_POINT
     */
    NULL_POINT(202, "空指针异常"),
    /**
     * 未授权
     */
    UNAUTHORIZED(401, "未授权"),
    /**
     * 没有访问权限
     */
    FORBIDDEN(403, "没有访问权限"),
    /**
     * UNKNOWN_ERROR
     */
    UNKNOWN_ERROR(500, "未知错误");

    /**
     * 响应状态码
     */
    private final Integer code;
    /**
     * 响应信息
     */
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
