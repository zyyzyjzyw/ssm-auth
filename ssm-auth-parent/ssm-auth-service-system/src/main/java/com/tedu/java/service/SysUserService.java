package com.tedu.java.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tedu.java.system.SysUser;
import com.tedu.java.vo.SysUserQueryVo;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-03
 */
public interface SysUserService extends IService<SysUser> {
    //用户列表方法
    IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo);

    void updateUserStatus(String id, Integer status);
    //根据用户名称进行查询
    SysUser getUserInfoByUserName(String username);

    Map<String, Object> getUserInfo(String username);
}
