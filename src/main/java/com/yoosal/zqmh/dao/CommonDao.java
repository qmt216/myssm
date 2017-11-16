package com.yoosal.zqmh.dao;

import com.yoosal.zqmh.pojo.Information;
import com.yoosal.zqmh.pojo.WhereCase;

import java.util.List;
import java.util.Map;

/**
 * Created by 秦明涛 on 2015/4/21.
 * Desc
 */
public interface CommonDao<T> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

    /*---------*/

    List<T> queryAll(Map<String, Object> param);

    int queryAllCount();

    int countByExample(WhereCase example);

    List<T> selectByExample(WhereCase example);
}
