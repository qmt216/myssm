package com.yoosal.zqmh.dao;

import com.yoosal.zqmh.pojo.Admin;

public interface AdminMapper extends CommonDao<Admin> {
    Admin selectByName(String name);
}