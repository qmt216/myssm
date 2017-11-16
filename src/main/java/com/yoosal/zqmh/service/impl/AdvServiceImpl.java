package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.AdvMapper;
import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.pojo.Adv;
import com.yoosal.zqmh.service.IAdvService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by qinmingtao on 2016/8/8.
 * Desc
 */
@Service("advService")
public class AdvServiceImpl extends CommonServiceImpl<Adv> implements IAdvService {
    @Resource
    AdvMapper advMapper;

    @Override
    protected CommonDao<Adv> getDao() {
        return advMapper;
    }

    @Override
    public List<Adv> getAdvByPosition(int positionId, int enable) {
        return advMapper.getAdvByPosition(positionId, enable);
    }
}
