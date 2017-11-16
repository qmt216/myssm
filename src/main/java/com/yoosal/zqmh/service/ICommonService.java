package com.yoosal.zqmh.service;

import java.util.List;

/**
 * Created by 秦明涛 on 2015/4/21.
 * Desc
 */
public interface ICommonService<T> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    List<T> queryAll(String orderBy, int start, int limit);

    int queryAllCount();

    int countByExample(String condition);

    List<T> selectByExample(String condition, String order, int start, int limit);
}
