package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.BrandMapper;
import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.pojo.Brand;
import com.yoosal.zqmh.service.IBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by qinmingtao on 2016/8/19.
 * Desc 品牌展示
 */
@Service
public class BrandServiceImpl extends CommonServiceImpl<Brand> implements IBrandService{
    @Resource
    BrandMapper brandMapper;
    @Override
    protected CommonDao<Brand> getDao() {
        return brandMapper;
    }
}
