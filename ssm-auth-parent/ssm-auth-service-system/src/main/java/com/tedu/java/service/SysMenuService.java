package com.tedu.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tedu.java.system.SysMenu;
import com.tedu.java.vo.AssginMenuVo;
import com.tedu.java.vo.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2022-11-05
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> findNodes();

    void removeMenuById(String id);

    void updateMenuStatus(String id, Integer status);
    //根据角色获取菜单
    List<SysMenu> findMenuByRoleId(String roleId);
    //给角色分配权限
    void doAssign(AssginMenuVo assginMenuVo);

    List<RouterVo> getUserMenuList(String id);

    List<String> getUserButtonList(String id);
}
