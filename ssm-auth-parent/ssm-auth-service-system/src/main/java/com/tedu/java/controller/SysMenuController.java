package com.tedu.java.controller;


import com.tedu.java.annotation.Log;
import com.tedu.java.enums.BusinessType;
import com.tedu.java.result.Result;
import com.tedu.java.service.SysMenuService;
import com.tedu.java.system.SysMenu;
import com.tedu.java.vo.AssginMenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author zyy
 * @since 2022-11-05
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation("给角色分配权限")
    @Log(title = "菜单管理",businessType = BusinessType.ASSGIN)
    @PostMapping("/doAssign")
    public Result doAssign(@RequestBody AssginMenuVo assginMenuVo) {
        sysMenuService.doAssign(assginMenuVo);
        return Result.ok();
    }

    //根据角色获取菜单
    @Log(title = "菜单管理",businessType = BusinessType.SELECT)
    @ApiOperation("根据角色获取菜单")
    @GetMapping("/toAssign/{roleId}")
    public Result toAssign(@PathVariable("roleId") String roleId){
       List<SysMenu> list =  sysMenuService.findMenuByRoleId(roleId);
       return Result.ok(list);
    }
    @Log(title = "菜单管理",businessType = BusinessType.STATUS)
    @ApiOperation("更改菜单状态")
    @GetMapping("/updateMenuStatus/{id}/{status}")
    public Result updateUserStatus(@PathVariable("id") String id,
                                   @PathVariable("status") Integer status){
        sysMenuService.updateMenuStatus(id,status);
        return Result.ok();
    }
    //删除菜单
    @Log(title = "菜单管理",businessType = BusinessType.DELETE)
    @ApiOperation("删除菜单")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable("id") String id){
        sysMenuService.removeMenuById(id);
        return Result.ok();

    }
    //修改
    @ApiOperation("修改菜单")
    @Log(title = "菜单管理",businessType = BusinessType.UPDATE)
    @PostMapping("update")
    public Result update(@RequestBody SysMenu sysMenu){
        boolean isSuccess = sysMenuService.updateById(sysMenu);
        if(isSuccess){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //根据id进行查询
    @ApiOperation("根据id进行查询列表")
    @Log(title = "菜单管理",businessType = BusinessType.SELECT)
    @GetMapping("findNode/{id}")
    public Result findNode(@PathVariable("id") String id){
        SysMenu sysMenu = sysMenuService.getById(id);
        return Result.ok(sysMenu);
    }
    //添加菜单
    @Log(title = "菜单管理",businessType = BusinessType.INSERT)
    @ApiOperation("添加菜单")
    @PostMapping("save")
    public Result save(@RequestBody SysMenu sysMenu){
        sysMenuService.save(sysMenu);
        return Result.ok();
    }

    //列单列表(树形)
    @Log(title = "菜单管理",businessType = BusinessType.SELECT)
    @ApiOperation("菜单列表")
    @GetMapping("findNodes")
    public Result findNodes(){
        List<SysMenu> list = sysMenuService.findNodes();
        return Result.ok(list);
    }

}

