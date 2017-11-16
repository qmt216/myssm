package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.AdvPositionMapper;
import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.pojo.AdvPosition;
import com.yoosal.zqmh.service.IAdvPositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by qmt on 2016/8/9.
 * Desc
 */
@Service
public class AdvPositionServiceImpl extends CommonServiceImpl<AdvPosition> implements IAdvPositionService {
    @Resource
    AdvPositionMapper advPositionMapper;

    @Override
    protected CommonDao<AdvPosition> getDao() {
        return advPositionMapper;
    }

    @Override
    public AdvPosition getAdvPositionByCode(String code, int enable) {
        return advPositionMapper.getAdvPositionByCode(code, enable);
    }
}
