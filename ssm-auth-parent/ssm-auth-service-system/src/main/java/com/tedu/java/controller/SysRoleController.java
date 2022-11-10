package com.tedu.java.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tedu.java.annotation.Log;
import com.tedu.java.enums.BusinessType;
import com.tedu.java.exception.OtherException;
import com.tedu.java.result.Result;
import com.tedu.java.service.SysRoleService;
import com.tedu.java.system.SysRole;
import com.tedu.java.vo.AssginRoleVo;
import com.tedu.java.vo.SysRoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author： zyy
 * @date： 2022/10/29 17:26
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Api(tags = "角色管理接口")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Log(title = "角色管理",businessType = BusinessType.ASSGIN)
    @PreAuthorize("hasAuthority('bnt.sysRole.assignAuth')")
    @ApiOperation("用户分配角色")
    @PostMapping("doAssign")
    public Result doAssign(@RequestBody AssginRoleVo assginRoleVo){
        sysRoleService.doAssign(assginRoleVo);
        return Result.ok();
    }
    @Log(title = "角色管理",businessType = BusinessType.SELECT)
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("获取用户角色的数据")
    @GetMapping("/toAssign/{userId}")
    public Result toAssign(@PathVariable String userId){
        Map<String,Object> roleMap = sysRoleService.getRolesByUserId(userId);
        return Result.ok(roleMap);
    }

    /**
     * 批量删除
     * 获取多个id
     * json数组格式对应java中的list集合
     */
    @Log(title = "角色管理",businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("批量删除")
    @DeleteMapping("/batchRemove")
    public Result batchRemove(@RequestBody List<Long> ids){
        boolean isSuccess = sysRoleService.removeByIds(ids);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    /**
     * 修改，最终的修改
     */
    @Log(title = "角色管理",businessType = BusinessType.UPDATE)
    @PreAuthorize("hasAuthority('bnt.sysRole.update')")
    @ApiOperation("最终修改")
    @PostMapping("/update")
    public Result updateRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.updateById(sysRole);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    /**
     * 修改，现根据id进行查询
     */
    @Log(title = "角色管理",businessType = BusinessType.SELECT)
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("根据id进行查询")
    @PostMapping("/findRoleById/{id}")
    public Result findRoleById(@PathVariable("id") Long id){
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }
    /**
     * 添加
     * @RequestBody不能使用get提交方式
     * 传递json格式数据，把json格式数据封装到对象里面
     */
    @Log(title = "角色管理",businessType = BusinessType.INSERT)
    @PreAuthorize("hasAuthority('bnt.sysRole.add')")
    @ApiOperation("添加角色")
    @PostMapping("/save")
    public Result saveRole(@RequestBody SysRole sysRole){
        boolean isSuccess = sysRoleService.save(sysRole);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }

    }

    /**
     * page 当前页 limit每页显示的行数
     * @return条件分页查询
     */
    @Log(title = "角色管理",businessType = BusinessType.SELECT)
    @PreAuthorize("hasAuthority('bnt.sysRole.list')")
    @ApiOperation("条件分页查询")
    @GetMapping("/{page}/{limit}")
    public Result findQueryRole(@ApiParam(name = "page", value = "当前页码", required = true)
                                    @PathVariable("page") Long page,
                                @ApiParam(name = "limit", value = "每页记录数", required = true)
                                    @PathVariable("limit") Long limit,
                                @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
                                    SysRoleQueryVo sysRoleQueryVo){
       //创建page对象
        Page<SysRole> pageParam = new Page<>(page,limit);
        //调用service方法
        IPage<SysRole> pageModel =  sysRoleService.selectPage(pageParam,sysRoleQueryVo);
        //返回
        return Result.ok(pageModel);
    }
    //逻辑删除接口
    @Log(title = "角色管理",businessType = BusinessType.DELETE)
    @PreAuthorize("hasAuthority('bnt.sysRole.remove')")
    @ApiOperation("逻辑删除接口")
    @DeleteMapping("/remove/{id}")
    public Result removeRole(@PathVariable("id") Long id){
        boolean isSuccess = sysRoleService.removeById(id);
        if(isSuccess){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }
    //查询所有记录
    @ApiOperation("查询所有接口")
    @GetMapping("/findAll")
    public Result findAllRole(){
        try{
            int i = 10/0;
        }catch (Exception e){
            throw new OtherException(20001,"执行自定义异常");
        }
        //调用service
        List<SysRole> list = sysRoleService.list();
        return Result.ok(list);
    }
}
