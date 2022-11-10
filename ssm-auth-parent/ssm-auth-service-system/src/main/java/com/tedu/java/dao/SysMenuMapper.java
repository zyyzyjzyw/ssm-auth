package com.tedu.java.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.java.system.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-11-05
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    //如果userId不是1，其他类型用户，查询这个用户权限
    List<SysMenu> findMenuListUserId(@Param("id") String id);
}
