package com.haohao.fast.common.exception;

import com.haohao.fast.common.result.ResultCodeEnum;
import com.haohao.fast.common.result.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常配置类
 *
 * @author haohao
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionConfig {

    /**
     * 通用异常处理方法
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultData error(Exception e) throws Exception {
        // 将springSecurity的异常交给对应处理器去处理
        if (e instanceof AccessDeniedException || e instanceof AuthenticationException) {
            throw e;
        }
        log.error("全局异常信息 \n", e);
        return ResultData.error().data(e.getMessage());
    }

    /**
     * 空指针异常处理方法
     **/
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResultData error(NullPointerException e) {
        log.error("空指针异常信息 \n", e);
        return ResultData.setResult(ResultCodeEnum.NULL_POINT);
    }

    /**
     * 参数验证异常处理方法
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultData error(MethodArgumentNotValidException e) {
        log.error("参数验证异常信息 \n", e);
        Map<String, String> errors = new HashMap<>(1);
        e.getBindingResult().getAllErrors().forEach(error -> errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return ResultData.setResult(ResultCodeEnum.PARAM_ERROR).data(errors);
    }
}
