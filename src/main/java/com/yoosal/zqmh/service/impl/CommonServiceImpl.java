package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.pojo.Information;
import com.yoosal.zqmh.pojo.WhereCase;
import com.yoosal.zqmh.service.ICommonService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 秦明涛 on 2015/4/21.
 * Desc
 */
public abstract class CommonServiceImpl<T> implements ICommonService<T> {
    protected abstract CommonDao<T> getDao();

    public int deleteByPrimaryKey(Integer id) {
        return getDao().deleteByPrimaryKey(id);
    }

    public int insert(T record) {
        return getDao().insert(record);
    }

    public int insertSelective(T record) {
        return getDao().insertSelective(record);
    }

    public T selectByPrimaryKey(Integer id) {
        return getDao().selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(T record) {
        return getDao().updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(T record) {
        return getDao().updateByPrimaryKey(record);
    }

    public List<T> queryAll(String orderBy, int start, int limit) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orderBy", orderBy);
        paramMap.put("start", start);
        paramMap.put("limit", limit);
        return getDao().queryAll(paramMap);
    }

    public int queryAllCount() {
        return getDao().queryAllCount();
    }

    public int countByExample(String condition) {
        return getDao().countByExample(new WhereCase(condition));
    }

    public List<T> selectByExample(String condition, String order, int start, int limit) {
        return getDao().selectByExample(new WhereCase(condition, order, start, limit));
    }
}
