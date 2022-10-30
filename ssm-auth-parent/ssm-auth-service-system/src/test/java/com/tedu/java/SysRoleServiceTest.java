package com.tedu.java;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.java.service.SysRoleService;
import com.tedu.java.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author： zyy
 * @date： 2022/10/29 12:24
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@SpringBootTest
public class SysRoleServiceTest {

    @Autowired
    private SysRoleService sysRoleService;
    //查询所有
    @Test
    public void findAll(){
        List<SysRole> list =
                sysRoleService.list();
        System.out.println(list);
    }
    //添加
    @Test
    public void add(){
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("测试名称");
        sysRole.setRoleCode("testManager");
        sysRole.setDescription("测试角色");
        sysRoleService.save(sysRole);
    }
    //修改
    @Test
    public void update(){
        SysRole byId = sysRoleService.getById(2);
        byId.setDescription("214234");
        sysRoleService.updateById(byId);
    }
    //删除
    @Test
    public void delete(){
        boolean b = sysRoleService.removeById(2);
        System.out.println(b);
    }
    //条件查询
    @Test
    public void select(){
        QueryWrapper<SysRole> sysRoleQueryWrapper = new QueryWrapper<>();
        sysRoleQueryWrapper.eq("role_name", "SYSTEM");
        List<SysRole> list = sysRoleService.list(sysRoleQueryWrapper);
        System.out.println(list);

    }
}
