package com.tedu.java.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tedu.java.system.SysPost;
import com.tedu.java.vo.SysPostQueryVo;

public interface SysPostService extends IService<SysPost> {
    void updatePostStatus(String id, Integer status);

    IPage<SysPost> selectPage(Long page, Long limit, SysPostQueryVo sysPostQueryVo);
}
