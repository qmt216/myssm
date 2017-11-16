package com.yoosal.zqmh.dao;

import com.yoosal.zqmh.pojo.AdvPosition;

public interface AdvPositionMapper extends CommonDao<AdvPosition> {
    AdvPosition getAdvPositionByCode(String code, int enable);
}