package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.dao.InformationMapper;
import com.yoosal.zqmh.pojo.Information;
import com.yoosal.zqmh.pojo.WhereCase;
import com.yoosal.zqmh.service.IInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by qmt on 2016/8/11.
 * Desc
 */
@Service("informationService")
public class InformationServiceImpl extends CommonServiceImpl<Information> implements IInformationService{
    @Resource
    InformationMapper informationMapper;
    @Override
    protected CommonDao<Information> getDao() {
        return informationMapper;
    }

    public int countByExample(WhereCase example) {
        return informationMapper.countByExample(example);
    }

    public List<Information> selectByExample(WhereCase example) {
        return informationMapper.selectByExample(example);
    }
}
