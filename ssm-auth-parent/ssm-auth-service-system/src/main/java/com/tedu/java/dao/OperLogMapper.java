package com.tedu.java.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.java.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author： zyy
 * @date： 2022/11/9 13:58
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Mapper
@Repository
public interface OperLogMapper extends BaseMapper<SysOperLog> {
}
