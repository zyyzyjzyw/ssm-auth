package com.tedu.java.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tedu.java.annotation.Log;
import com.tedu.java.enums.BusinessType;
import com.tedu.java.result.Result;
import com.tedu.java.service.SysPostService;
import com.tedu.java.system.SysPost;
import com.tedu.java.vo.SysPostQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author： zyy
 * @date： 2022/11/9 17:38
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Api(tags = "岗位管理")
@RestController
@RequestMapping("/admin/system/sysPost")
public class SysPostController {

    @Autowired
    private SysPostService sysPostService;
    @Log(title = "岗位管理",businessType = BusinessType.UPDATE)
    @ApiOperation("更改岗位状态")
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateUserStatus(@PathVariable("id") String id,
                                   @PathVariable("status") Integer status){
        sysPostService.updatePostStatus(id,status);
        return Result.ok();
    }

    @Log(title = "岗位管理",businessType = BusinessType.SELECT)
    @ApiOperation(value = "获取岗位分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "userQueryVo", value = "查询对象", required = false)
                    SysPostQueryVo sysPostQueryVo) {
        IPage<SysPost> pageModel = sysPostService.selectPage(page,limit,sysPostQueryVo);
        return Result.ok(pageModel);
    }
    @Log(title = "岗位管理",businessType = BusinessType.SELECT)
    @ApiOperation(value = "获取用户")
    @GetMapping("/get/{id}")
    public Result get(@PathVariable Long id) {
        SysPost user = sysPostService.getById(id);
        return Result.ok(user);
    }
    @Log(title = "岗位管理",businessType = BusinessType.INSERT)
    @ApiOperation(value = "保存用户")
    @PostMapping("/save")
    public Result save(@RequestBody SysPost sysPost) {

        sysPostService.save(sysPost);
        return Result.ok();
    }

    @Log(title = "岗位管理",businessType = BusinessType.UPDATE)
    @ApiOperation(value = "更新用户")
    @PutMapping("/update")
    public Result updateById(@RequestBody SysPost sysPost) {
        sysPostService.updateById(sysPost);
        return Result.ok();
    }

    @Log(title = "岗位管理",businessType = BusinessType.DELETE)
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id) {
        sysPostService.removeById(id);
        return Result.ok();
    }
}
