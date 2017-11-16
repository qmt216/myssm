package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.dao.UserMapper;
import com.yoosal.zqmh.pojo.User;
import com.yoosal.zqmh.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("userService")
public class UserServiceImpl extends CommonServiceImpl<User> implements IUserService {
    @Resource
    protected UserMapper userMapper;

    @Override
    protected CommonDao<User> getDao() {
        return userMapper;
    }
}
