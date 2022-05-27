package com.haohao.fast.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.fast.common.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * @author haohao
 * @TableName sys_dict
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("系统字典实体类")
@TableName(value = "sys_dict")
@EqualsAndHashCode(callSuper = true)
public class SysDictEntity extends BaseEntity {
    /**
     * 字典ID
     */
    @TableId
    private Long dictId;
    /**
     * 字典编码
     */
    private String dictCode;
    /**
     * 字典名称
     */
    private String dictName;
    /**
     * 字典状态:(0:正常 1:禁用)
     */
    private Integer state;
    /**
     * 字典排序
     */
    private Integer sort;
}