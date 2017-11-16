package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.dao.LinkMapper;
import com.yoosal.zqmh.pojo.Link;
import com.yoosal.zqmh.service.ILinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by qmt on 2016/8/28.
 * Desc 友情链接
 */
@Service
public class ILinkServiceImpl extends CommonServiceImpl<Link> implements ILinkService{
    @Resource
    LinkMapper linkMapper;

    @Override
    protected CommonDao<Link> getDao() {
        return linkMapper;
    }
}
