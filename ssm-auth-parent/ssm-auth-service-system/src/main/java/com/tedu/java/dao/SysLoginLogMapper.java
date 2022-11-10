package com.tedu.java.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.java.system.SysLoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SysLoginLogMapper extends BaseMapper<SysLoginLog> {
}
