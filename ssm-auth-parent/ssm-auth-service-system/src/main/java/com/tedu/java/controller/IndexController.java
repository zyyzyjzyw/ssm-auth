package com.tedu.java.controller;

import com.tedu.java.annotation.Log;
import com.tedu.java.enums.BusinessType;
import com.tedu.java.exception.OtherException;
import com.tedu.java.result.Result;
import com.tedu.java.service.SysUserService;
import com.tedu.java.system.SysUser;
import com.tedu.java.utils.JwtHelper;
import com.tedu.java.utils.MD5;
import com.tedu.java.vo.LoginVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author： zyy
 * @date： 2022/10/30 15:20
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Api(tags = "后台用户登录管理")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    @Autowired
    private SysUserService sysUserService;
    /**
     * login
     * 响应格式：{"code":20000,"data":{"token":"admin-token"}}
     */
    @Log(title = "登录管理",businessType = BusinessType.LOGIN)
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        //根据用户名称查询数据
        SysUser sysUser = sysUserService.getUserInfoByUserName(loginVo.getUsername());
        //如果查询数据为空
        if(sysUser==null){
            throw new OtherException(201,"用户不存在");
        }
        //判断密码是否一致
        String loginPassword = loginVo.getPassword();
        if(!sysUser.getPassword().equalsIgnoreCase(MD5.encrypt(loginPassword))){
            throw new OtherException(201,"您输入的密码不正确");
        }
        //判断当前用户是否可用
        if(sysUser.getStatus().intValue()==0){
            throw new OtherException(201,"用户已经被禁用");
        }
        //根据userId和username生成token字符串，通过map返回
        String token = JwtHelper.createToken(sysUser.getId(), sysUser.getUsername());
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return Result.ok(map);
    }
    /**
     * info
     * 响应格式：
     * {"code":20000,
     * "data":{
     * "roles":["admin"],
     * "introduction":"I am a super administrator",
     * "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
     * "name":"Super Admin"}}
     */
    @GetMapping("/info")
    public Result info(HttpServletRequest request){
        //获取请求头token字符串
        String token = request.getHeader("token");
        //从token字符串中获取用户名称
        String username = JwtHelper.getUsername(token);
        //根据用户名称获取用户信息(基本信息和菜单权限和按钮权限数据)
        Map<String,Object> map = sysUserService.getUserInfo(username);
       /* Map<String,Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("introduction","I am a super administrator");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","Super Admin ZYY");*/
        return Result.ok(map);
    }

}
