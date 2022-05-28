package com.haohao.fast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haohao.fast.domain.SysUserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author haohao
 * @description 针对表【sys_user】的数据库操作Mapper
 * @createDate 2022-05-17 17:28:08
 * @Entity com.haohao.fast.domain.SysUser
 */
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 真删除
     *
     * @param id 用户ID
     * @return 影响行数
     */
    int reallyDeleteById(@Param("id") Long id);

    /**
     * 真删除
     *
     * @param ids 用户IDS
     * @return 影响行数
     */
    int reallyDeleteByIds(@Param("ids") Long[] ids);
}
