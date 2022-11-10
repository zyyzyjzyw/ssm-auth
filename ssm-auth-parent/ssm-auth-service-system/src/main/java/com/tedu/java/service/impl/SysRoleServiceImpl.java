package com.tedu.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tedu.java.dao.SysRoleMapper;
import com.tedu.java.dao.SysUserRoleMapper;
import com.tedu.java.service.SysRoleService;
import com.tedu.java.system.SysRole;
import com.tedu.java.system.SysUserRole;
import com.tedu.java.vo.AssginRoleVo;
import com.tedu.java.vo.SysRoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author： zyy
 * @date： 2022/10/29 12:21
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    //分页查询角色
    @Override
    public IPage<SysRole> selectPage(Page<SysRole> pageParam, SysRoleQueryVo sysRoleQueryVo) {
        IPage<SysRole> pageModel = baseMapper.selectPages(pageParam,sysRoleQueryVo);
        return pageModel;
    }
    //根据userId获取所有角色
    @Override
    public Map<String, Object> getRolesByUserId(String userId) {
        //获取所有角色
        List<SysRole> roles = baseMapper.selectList(null);
        //根据用户di查询已分配的角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        List<SysUserRole> sysUserRoleList = sysUserRoleMapper.selectList(wrapper);
        //userRole集合获取所有的角色id
        List<String> userRoleIds = new ArrayList<>();
        for(SysUserRole userRole : sysUserRoleList){
            userRoleIds.add(userRole.getRoleId()) ;
        }
        //封装到map集合
        //创建返回的Map
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("allRoles",roles);//所有用户角色
        returnMap.put("userRoleIds",userRoleIds);//用户当前的角色
        return returnMap;
    }
    //用户分配角色
    @Override
    public void doAssign(AssginRoleVo assginRoleVo) {
        //根据用户id删除之前的分配角色
        QueryWrapper<SysUserRole> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",assginRoleVo.getUserId());
        sysUserRoleMapper.delete(wrapper);
        //获取所有角色id,添加用户角色关系
        //角色id列表
        List<String> roleIdList = assginRoleVo.getRoleIdList();
        for(String roleId:roleIdList){
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(assginRoleVo.getUserId());
            userRole.setRoleId(roleId);
            sysUserRoleMapper.insert(userRole);
        }
    }
}
