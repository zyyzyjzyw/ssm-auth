package com.tedu.java.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tedu.java.dao.SysRoleMapper;
import com.tedu.java.service.SysRoleService;
import com.tedu.java.system.SysRole;
import com.tedu.java.vo.SysRoleQueryVo;
import org.springframework.stereotype.Service;

/**
 * @author： zyy
 * @date： 2022/10/29 12:21
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPages(pageParam,sysRoleQueryVo);
        return pageModel;
    }
}
