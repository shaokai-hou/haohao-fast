package com.haohao.fast.domain.router;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 路由显示信息
 *
 * @author haohao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meta {

    /**
     * 菜单名称
     */
    private String title;
    /**
     * 菜单图标
     */
    private String icon;
}
