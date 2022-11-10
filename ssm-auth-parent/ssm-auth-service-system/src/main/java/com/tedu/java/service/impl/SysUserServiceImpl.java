package com.tedu.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tedu.java.dao.SysUserMapper;
import com.tedu.java.service.SysMenuService;
import com.tedu.java.service.SysUserService;
import com.tedu.java.system.SysUser;
import com.tedu.java.vo.RouterVo;
import com.tedu.java.vo.SysUserQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-03
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysMenuService sysMenuService;
    //用户列表
    @Override
    public IPage<SysUser> selectPage(Page<SysUser> pageParam, SysUserQueryVo sysUserQueryVo) {
        return baseMapper.selectPage(pageParam,sysUserQueryVo);
    }

    @Override
    public void updateUserStatus(String id, Integer status) {
        //根据用户id查询
        SysUser sysUser = baseMapper.selectById(id);
        //设置修改状态
        sysUser.setStatus(status);
        //调用方法修改
        baseMapper.updateById(sysUser);
    }
    //根据用户名称查询数据
    @Override
    public SysUser getUserInfoByUserName(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return baseMapper.selectOne(wrapper);
    }
    //根据用户名称获取用户信息(基本信息和菜单权限和按钮权限数据)
    @Override
    public Map<String, Object> getUserInfo(String username) {
        //根据用户名称查询用户的基本信息
        SysUser sysUser = this.getUserInfoByUserName(username);
        //根据userId查询菜单权限值
        List<RouterVo> routerVoList = sysMenuService.getUserMenuList(sysUser.getId());
        //根据userId查询按钮权限值
        List<String> permsList =  sysMenuService.getUserButtonList(sysUser.getId());
        Map<String,Object> result = new HashMap<>();
        result.put("name",username);
        result.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles","[admin]");
        //菜单的权限控制
        result.put("routers",routerVoList);
        //按钮的权限控制
        result.put("buttons",permsList);
        return result;
    }
}
