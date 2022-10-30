package com.tedu.java;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.java.dao.SysRoleMapper;
import com.tedu.java.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author： zyy
 * @date： 2022/10/29 10:58
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@SpringBootTest
public class SysRoleMapperTest {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    //查询表中所有数据
    @Test
    public void findAll(){
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        for (SysRole sysRole:sysRoles){
            System.out.println(sysRole);
        }
    }
    //添加造作
    @Test
    public void add(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试名称");
        sysRole.setRoleCode("testManager");
        sysRole.setDescription("测试角色");
        int insert = sysRoleMapper.insert(sysRole);
        System.out.println(insert);
    }
    //修改操作
    @Test
    public void testUpdate(){
        //根据id查询
        SysRole sysRole = sysRoleMapper.selectById(9);
        //设置修改值
        sysRole.setDescription("修改123");
        //调用方法修改
        sysRoleMapper.updateById(sysRole);
    }
    //批量删除
    @Test
    public void testBatchDelete(){
        int i = sysRoleMapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(i);
    }
    //条件查询
    @Test
    public void testSelect(){
        //创建条件构造器
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        //设置条件
        sysRoleQueryWrapper.eq("role_name","普通管理员");
        //调用方法查询
        List<SysRole> sysRoles = sysRoleMapper.selectList(sysRoleQueryWrapper);
        System.out.println(sysRoles);
    }

    //条件删除
    @Test
    public void testDelete(){
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.eq("role_name","普通管理员");
        sysRoleMapper.delete(sysRoleQueryWrapper);
    }

}
