package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.dao.InformationCateMapper;
import com.yoosal.zqmh.pojo.InformationCate;
import com.yoosal.zqmh.pojo.InformationCateExample;
import com.yoosal.zqmh.service.IInformationCateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by qmt on 2016/8/11.
 * Desc
 */
@Service("informationCateService")
public class InformationCateServiceImpl extends CommonServiceImpl<InformationCate> implements IInformationCateService {
    @Resource
    InformationCateMapper informationCateMapper;

    @Override
    protected CommonDao<InformationCate> getDao() {
        return informationCateMapper;
    }

}
