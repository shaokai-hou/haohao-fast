package com.haohao.fast.common.result;

import java.util.List;

/**
 * 通用分页返回
 *
 * @author haohao
 * @date 2022年05月31日 14:42
 */
public class PageResult<T> {

    /**
     * 分页数据
     */
    private List<T> data;
    /**
     * 总页数
     */
    private Long total;
}
