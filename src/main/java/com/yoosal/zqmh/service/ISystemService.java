package com.yoosal.zqmh.service;

import com.yoosal.zqmh.pojo.SystemWithBLOBs;

/**
 * Created by qmt on 2016/8/7.
 * Desc
 */
public interface ISystemService extends ICommonService<SystemWithBLOBs> {
    int updateByPrimaryKeyWithBLOBs(SystemWithBLOBs systemWithBLOBs);
}
