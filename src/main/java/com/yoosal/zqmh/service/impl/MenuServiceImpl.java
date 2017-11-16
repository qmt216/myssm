package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.dao.MenuMapper;
import com.yoosal.zqmh.pojo.Menu;
import com.yoosal.zqmh.service.IMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by qmt on 2016/8/7.
 * Desc
 */
@Service("menuService")
public class MenuServiceImpl extends CommonServiceImpl<Menu> implements IMenuService{
    @Resource
    protected MenuMapper menuMapper;

    @Override
    protected CommonDao<Menu> getDao() {
        return menuMapper;
    }
}
