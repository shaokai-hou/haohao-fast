package com.haohao.fast.common.bean;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础控制器
 *
 * @author haohao
 * @date 2022年05月28日 15:08
 */
public class BaseController<T> {

    /**
     * 当前页码数
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 获取分页对象
     *
     * @return Page<T>
     */
    public Page<T> getPage() {
        Integer pageNum = Convert.toInt(getParameter(PAGE_NUM), 1);
        Integer pageSize = Convert.toInt(getParameter(PAGE_SIZE), 10);
        return new Page<>(pageNum, pageSize);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

}
