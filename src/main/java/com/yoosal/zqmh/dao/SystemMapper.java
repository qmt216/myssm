package com.yoosal.zqmh.dao;

import com.yoosal.zqmh.pojo.SystemWithBLOBs;

public interface SystemMapper extends CommonDao<SystemWithBLOBs>{
    int updateByPrimaryKeyWithBLOBs(SystemWithBLOBs systemWithBLOBs);
}