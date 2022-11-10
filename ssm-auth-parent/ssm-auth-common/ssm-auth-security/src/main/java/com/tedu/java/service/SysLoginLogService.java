package com.tedu.java.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tedu.java.system.SysLoginLog;
import com.tedu.java.vo.SysLoginLogQueryVo;

/**
 * @author： zyy
 * @date： 2022/11/9 12:32
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
public interface SysLoginLogService {
    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status   状态
     * @param ipaddr   ip
     * @param message  消息内容
     * @return
     */
    void recordLoginLog(String username, Integer status, String ipaddr, String message);

    IPage<SysLoginLog> selectPage(long page, long limit, SysLoginLogQueryVo sysLoginLogQueryVo);
}
