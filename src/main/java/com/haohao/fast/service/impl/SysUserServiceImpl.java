package com.haohao.fast.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haohao.fast.domain.SysUserEntity;
import com.haohao.fast.mapper.SysUserMapper;
import com.haohao.fast.service.SysUserService;
import org.springframework.stereotype.Service;

/**
* @author haohao
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2022-05-17 17:28:08
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity>
implements SysUserService{

}
