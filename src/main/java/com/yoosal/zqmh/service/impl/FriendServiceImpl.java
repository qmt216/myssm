package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.dao.FriendMapper;
import com.yoosal.zqmh.pojo.Friend;
import com.yoosal.zqmh.service.IFriendService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by qmt on 2016/8/22.
 * Desc 友情链接
 */
@Service
public class FriendServiceImpl extends CommonServiceImpl<Friend> implements IFriendService {
    @Resource
    FriendMapper friendMapper;
    @Override
    protected CommonDao<Friend> getDao() {
        return friendMapper;
    }
}
