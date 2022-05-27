package com.haohao.fast.common.result;

import lombok.Data;

import java.io.Serializable;


/**
 * 统一返回结果
 *
 * @author haohao
 */
@Data
public class ResultData implements Serializable {

    private Integer code;

    private String message;

    private Object data;

    private ResultData() {
    }

    public static ResultData success() {
        ResultData result = new ResultData();
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    public static ResultData error() {
        ResultData result = new ResultData();
        result.setCode(ResultCodeEnum.UNKNOWN_ERROR.getCode());
        result.setMessage(ResultCodeEnum.UNKNOWN_ERROR.getMessage());
        return result;
    }

    public static ResultData setResult(ResultCodeEnum resultCode) {
        ResultData result = new ResultData();
        result.setCode(resultCode.getCode());
        result.setMessage(resultCode.getMessage());
        return result;
    }

    public static ResultData flag(Boolean flag) {
        return flag ? ResultData.success() : ResultData.error();
    }

    /**
     * ------------使用链式编程，返回类本身-----------
     **/

    public ResultData data(Object data) {
        this.setData(data);
        return this;
    }

    public ResultData message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultData code(Integer code) {
        this.setCode(code);
        return this;
    }
}
