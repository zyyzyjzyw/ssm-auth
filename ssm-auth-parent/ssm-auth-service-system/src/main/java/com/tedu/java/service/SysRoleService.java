package com.tedu.java.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tedu.java.system.SysRole;
import com.tedu.java.vo.SysRoleQueryVo;

public interface SysRoleService extends IService<SysRole> {
    /**
     * 条件分页查询
     * @param pageParam
     * @param sysRoleQueryVo
     * @return
     */
    IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo);
}
