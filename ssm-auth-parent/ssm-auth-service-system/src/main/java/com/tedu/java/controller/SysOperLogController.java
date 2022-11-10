package com.tedu.java.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tedu.java.annotation.Log;
import com.tedu.java.enums.BusinessType;
import com.tedu.java.result.Result;
import com.tedu.java.service.OperLogService;
import com.tedu.java.system.SysOperLog;
import com.tedu.java.vo.SysOperLogQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author： zyy
 * @date： 2022/11/9 14:43
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Api(value = "SysOperLog管理", tags = "SysOperLog管理")
@RestController
@RequestMapping(value="/admin/system/sysOperLog")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SysOperLogController {
    @Resource
    private OperLogService operLogService;
    @Log(title = "操作日志",businessType = BusinessType.SELECT)
    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "sysOperLogVo", value = "查询对象", required = false)
                    SysOperLogQueryVo sysOperLogQueryVo) {
        IPage<SysOperLog> pageModel = operLogService.selectPage(page,limit, sysOperLogQueryVo);
        return Result.ok(pageModel);
    }

    /*@ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SysOperLog sysOperLog = operLogService.getById(id);
        return Result.ok(sysOperLog);
    }*/
}
