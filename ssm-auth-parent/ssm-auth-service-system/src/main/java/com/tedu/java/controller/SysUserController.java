package com.tedu.java.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tedu.java.annotation.Log;
import com.tedu.java.enums.BusinessType;
import com.tedu.java.result.Result;
import com.tedu.java.service.SysUserService;
import com.tedu.java.system.SysUser;
import com.tedu.java.utils.MD5;
import com.tedu.java.vo.SysUserQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author zyy
 * @since 2022-11-03
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Log(title = "用户管理",businessType = BusinessType.UPDATE)
    @ApiOperation("更改用户状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateUserStatus(@PathVariable("id") String id,
                                   @PathVariable("status") Integer status){
        sysUserService.updateUserStatus(id,status);
        return Result.ok();
    }

    @Log(title = "用户管理",businessType = BusinessType.SELECT)
    @ApiOperation(value = "获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "userQueryVo", value = "查询对象", required = false)
                    SysUserQueryVo userQueryVo) {
        Page<SysUser> pageParam = new Page<>(page, limit);
        IPage<SysUser> pageModel = sysUserService.selectPage(pageParam, userQueryVo);
        return Result.ok(pageModel);
    }

    @Log(title = "用户管理",businessType = BusinessType.SELECT)
    @ApiOperation(value = "获取用户")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id) {
        SysUser user = sysUserService.getById(id);
        return Result.ok(user);
    }

    @Log(title = "用户管理",businessType = BusinessType.INSERT)
    @ApiOperation(value = "保存用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysUser user) {
        //把输入的密码进行加密 MD5
        String passwordMd5 = MD5.encrypt(user.getPassword());
        user.setPassword(passwordMd5);
        sysUserService.save(user);
        return Result.ok();
    }

    @Log(title = "用户管理",businessType = BusinessType.UPDATE)
    @ApiOperation(value = "更新用户")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysUser user) {
        sysUserService.updateById(user);
        return Result.ok();
    }

    @Log(title = "用户管理",businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id) {
        sysUserService.removeById(id);
        return Result.ok();
    }
}

