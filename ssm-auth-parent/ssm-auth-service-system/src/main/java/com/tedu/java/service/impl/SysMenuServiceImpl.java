package com.tedu.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tedu.java.dao.SysMenuMapper;
import com.tedu.java.dao.SysRoleMenuMapper;
import com.tedu.java.exception.OtherException;
import com.tedu.java.service.SysMenuService;
import com.tedu.java.system.SysMenu;
import com.tedu.java.system.SysRoleMenu;
import com.tedu.java.util.MenuHelper;
import com.tedu.java.util.RouterHelper;
import com.tedu.java.vo.AssginMenuVo;
import com.tedu.java.vo.RouterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-05
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysMenu> findNodes() {
        //获取所有的菜单
        List<SysMenu> sysMenuList = baseMapper.selectList(null);
        //所有的菜单数据转换成element-ui所要求的格式
        List<SysMenu> resultList = MenuHelper.buildTree(sysMenuList);
        return resultList;
    }
    //删除菜单
    @Override
    public void removeMenuById(String id) {
        //查询当前菜单下面是否还有子菜单
        //根据id和parentId进行判断
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count>0){
            //有子菜单
            throw new OtherException(201,"请先删除子菜单!!!");
        }
        //调用删除
        baseMapper.deleteById(id);
    }

    @Override
    public void updateMenuStatus(String id, Integer status) {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count>0){
            //有子菜单
            throw new OtherException(201,"请先删除子菜单!!!");
        }
        //调用修改
        SysMenu sysMenu = baseMapper.selectById(id);
        sysMenu.setStatus(status);
        baseMapper.updateById(sysMenu);
    }
    //根据角色获取菜单
    @Override
    public List<SysMenu> findMenuByRoleId(String roleId) {
        //获取所有的菜单 status=1
        QueryWrapper<SysMenu> wrapperMenu = new QueryWrapper();
        wrapperMenu.eq("status",1);
        List<SysMenu> menuList = baseMapper.selectList(wrapperMenu);
        //根据角色id查询，角色分配过的菜单列表
        QueryWrapper<SysRoleMenu> wrapperRoleMenu = new QueryWrapper();
        wrapperRoleMenu.eq("role_id",roleId);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(wrapperRoleMenu);
        //从第二部查询列表中，获取角色分配所有的菜单id
        List<String> roleMenuIds = new ArrayList<>();
        for(SysRoleMenu sysRoleMenu:sysRoleMenus){
            String menuId = sysRoleMenu.getMenuId();
            roleMenuIds.add(menuId);
        }
        //数据处理:isSelect 如果菜单被选中 true 否者false  拿着分配菜单id和所有菜单比对，有相同的让isSelect为true
        for(SysMenu sysMenu:menuList){
            if(roleMenuIds.contains(sysMenu.getId())){
                sysMenu.setSelect(true);
            }else {
                sysMenu.setSelect(false);
            }
        }
        //转换成属性结构为了最终显示
        List<SysMenu> sysMenus = MenuHelper.buildTree(menuList);
        return sysMenus;
    }

    @Override
    public void doAssign(AssginMenuVo assginMenuVo) {
        //根据角色id删除菜单权限
        QueryWrapper<SysRoleMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",assginMenuVo.getRoleId());
        sysRoleMenuMapper.delete(wrapper);
        //遍历菜单id列表，然后添加
        List<String> menuIdList = assginMenuVo.getMenuIdList();
        for(String menuId:menuIdList){
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(assginMenuVo.getRoleId());
            sysRoleMenuMapper.insert(sysRoleMenu);
        }
    }
    //根据userId查询菜单权限值
    @Override
    public List<RouterVo> getUserMenuList(String id) {
        //admin是超级管理员，操作所有内容
        List<SysMenu> sysMenuList = null;
        //判断userId的值是1，就是超级管理员，查询所有权限数据
        if("1".equalsIgnoreCase(id)){
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.eq("status",1);
            wrapper.orderByAsc("sort_value");
            sysMenuList= baseMapper.selectList(wrapper);
        }else{
            //如果userId不是1，其他类型用户，查询这个用户权限
            sysMenuList = baseMapper.findMenuListUserId(id);
        }
        //构建是树形结构
        List<SysMenu> sysMenuTreeList = MenuHelper.buildTree(sysMenuList);
        //转换成前端路由需要的格式
        List<RouterVo> routerVoList = RouterHelper.buildRouters(sysMenuTreeList);
        return routerVoList;
    }
    //根据userId查询按钮权限值
    @Override
    public List<String> getUserButtonList(String id) {
        List<SysMenu> sysMenuList =null;
        if("1".equalsIgnoreCase(id)){
            sysMenuList = baseMapper.selectList(new QueryWrapper<SysMenu>().eq("status", 1));
        }else{
            sysMenuList = baseMapper.findMenuListUserId(id);
        }
        List<String> permissionList = new ArrayList<>();
        //sysMenuList遍历，获取type=2
        for (SysMenu sysMenu:sysMenuList){
            if(sysMenu.getType()==2){
                permissionList.add(sysMenu.getPerms());
            }
        }
        return permissionList;
    }
}
