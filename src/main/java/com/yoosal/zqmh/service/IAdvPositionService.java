package com.yoosal.zqmh.service;

import com.yoosal.zqmh.pojo.AdvPosition;

/**
 * Created by qinmingtao on 2016/8/8.
 * Desc
 */
public interface IAdvPositionService extends ICommonService<AdvPosition> {
    AdvPosition getAdvPositionByCode(String code, int enable);
}
