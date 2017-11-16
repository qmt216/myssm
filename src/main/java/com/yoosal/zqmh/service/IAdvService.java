package com.yoosal.zqmh.service;

import com.yoosal.zqmh.pojo.Adv;

import java.util.List;

/**
 * Created by qinmingtao on 2016/8/8.
 * Desc
 */
public interface IAdvService extends ICommonService<Adv> {
    List<Adv> getAdvByPosition(int positionId, int enable);
}
