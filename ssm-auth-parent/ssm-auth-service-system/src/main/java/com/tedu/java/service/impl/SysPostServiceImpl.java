package com.tedu.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tedu.java.dao.SysPostMapper;
import com.tedu.java.service.SysPostService;
import com.tedu.java.system.SysPost;
import com.tedu.java.vo.SysPostQueryVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author： zyy
 * @date： 2022/11/9 17:43
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Service
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper, SysPost> implements SysPostService {
    @Autowired
    private SysPostMapper sysPostMapper;

    @Override
    public void updatePostStatus(String id, Integer status) {
        //根据用户id查询
        SysPost sysPost = baseMapper.selectById(id);
        //设置修改状态
        sysPost.setStatus(status);
        //调用方法修改
        baseMapper.updateById(sysPost);
    }

    @Override
    public IPage<SysPost> selectPage(Long page, Long limit, SysPostQueryVo sysPostQueryVo) {
        Page<SysPost> param = new Page(page,limit);
        String name = sysPostQueryVo.getName();
        String postCode = sysPostQueryVo.getPostCode();
        Boolean status = sysPostQueryVo.getStatus();
        QueryWrapper<SysPost> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(postCode)){
            wrapper.like("post_code",postCode);
        }
        Page<SysPost> sysPostPage = sysPostMapper.selectPage(param, wrapper);
        return sysPostPage;
    }

}
