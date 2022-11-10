package com.tedu.java.util;

import com.tedu.java.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author： zyy
 * @date： 2022/11/5 13:00
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
public class MenuHelper {
    //构建树形结构
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //创建集合封装最终数据
        List<SysMenu> trees = new ArrayList<>();
        //遍历所有菜单集合
        for(SysMenu sysMenu:sysMenuList){
            //找到递归入口,parentId=0
            if(sysMenu.getParentId().longValue()==0){
                trees.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }
    //从根节点进行递归查询，查询子节点   判断id=parentId是否相同，进行数据封装
    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        //数据初始化
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历递归查找
        for(SysMenu it:sysMenuList){
            /*
            //获取当前菜单id
            String id = sysMenu.getId();
            long cid = Long.parseLong(id);
            //获取所有菜单parentId
            Long parentId = it.getParentId();
            */
            //比对
            if(Long.parseLong(sysMenu.getId()) == it.getParentId()){
                if(sysMenu.getChildren()==null){
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(findChildren(it,sysMenuList));
            }
        }
        return sysMenu;
    }
}
