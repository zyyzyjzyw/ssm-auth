package com.tedu.java.service.impl;

import com.tedu.java.custom.CustomUser;
import com.tedu.java.service.SysMenuService;
import com.tedu.java.service.SysUserService;
import com.tedu.java.system.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author： zyy
 * @date： 2022/11/8 19:33
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getUserInfoByUserName(username);
        if(sysUser==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        if(sysUser.getStatus().intValue()==0){
            throw new RuntimeException("用户被禁用");
        }
        //根据用户id查询权限数据
        List<String> userButtonList = sysMenuService.getUserButtonList(sysUser.getId());
        //转换成security要求的格式
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(String buttons:userButtonList){
            authorities.add(new SimpleGrantedAuthority(buttons.trim()));
        }
        return new CustomUser(sysUser, authorities);
    }
}
