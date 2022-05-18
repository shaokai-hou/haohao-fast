package com.haohao.fast.common.bean;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基础Entity
 *
 * @author haohao
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除:(0:正常 1：已删除)
     */
    private Integer deleted;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;

    /**
     * 创建者
     */
    private Long creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private Long updater;

    /**
     * 更新时间
     */
    private Date updateTime;
}
