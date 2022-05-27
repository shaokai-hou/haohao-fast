package com.haohao.fast.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.fast.common.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;

/**
 * @author haohao
 * @TableName sys_dict_data
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("系统字典数据实体类")
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_dict_data")
public class SysDictData extends BaseEntity {
    /**
     * 字典数据ID
     */
    @TableId
    private Long dictDataId;
    /**
     * 字典编码
     */
    private String dictCode;
    /**
     * 字典项名称
     */
    private String dictLabel;
    /**
     * 字典项值
     */
    private String dictValue;
    /**
     * 排序
     */
    private Integer sort;
}