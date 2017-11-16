package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.dao.SystemMapper;
import com.yoosal.zqmh.pojo.SystemWithBLOBs;
import com.yoosal.zqmh.service.ISystemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by qmt on 2016/8/7.
 * Desc
 */
@Service("systemService")
public class SystemServiceImpl extends CommonServiceImpl<SystemWithBLOBs> implements ISystemService {
    @Resource
    SystemMapper systemMapper;

    @Override
    protected CommonDao<SystemWithBLOBs> getDao() {
        return systemMapper;
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(SystemWithBLOBs systemWithBLOBs) {
        return systemMapper.updateByPrimaryKeyWithBLOBs(systemWithBLOBs);
    }
}
