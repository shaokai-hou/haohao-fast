package com.haohao.fast.domain.router;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 路由基础信息
 *
 * @author haohao
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Router {

    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件名字
     */
    private String component;
    /**
     * 路由名字
     */
    private String name;
    /**
     * 路由显示信息
     */
    private Meta meta;
    /**
     * 是否显示
     */
    private boolean hidden;
    /**
     * 子路由
     */
    private List<Router> children;

    public Router(Tree<Long> tree) {
        this.path = MapUtil.get(tree, "path", String.class);
        this.name = tree.getName().toString();
        this.component = MapUtil.get(tree, "component", String.class);
        this.meta = new Meta(tree.getName().toString(), MapUtil.get(tree, "icon", String.class));
        this.children = CollUtil.isEmpty(tree.getChildren()) ?
                new ArrayList<>() :
                tree.getChildren().stream().map(Router::new).collect(Collectors.toList());
    }
}
