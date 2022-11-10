package com.tedu.java.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tedu.java.system.SysOperLog;
import com.tedu.java.vo.SysOperLogQueryVo;

public interface OperLogService {

    public void saveSysLog(SysOperLog sysOperLog);

    IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo);
}
