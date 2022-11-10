package com.tedu.java.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tedu.java.annotation.Log;
import com.tedu.java.enums.BusinessType;
import com.tedu.java.result.Result;
import com.tedu.java.service.SysLoginLogService;
import com.tedu.java.system.SysLoginLog;
import com.tedu.java.vo.SysLoginLogQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "SysLoginLog管理", tags = "SysLoginLog管理")
@RestController
@RequestMapping(value="/admin/system/sysLoginLog")
public class SysLoginLogController {

    @Autowired
    private SysLoginLogService loginLogService;

    //条件分页查询登录日志
    @Log(title = "登录日志",businessType = BusinessType.SELECT)
    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(@PathVariable long page,
                        @PathVariable long limit,
                        SysLoginLogQueryVo sysLoginLogQueryVo) {
       IPage<SysLoginLog> pageModel = loginLogService.selectPage(page,limit,sysLoginLogQueryVo);
        return Result.ok(pageModel);
    }
}
