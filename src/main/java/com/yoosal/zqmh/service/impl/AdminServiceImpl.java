package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.AdminMapper;
import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.pojo.Admin;
import com.yoosal.zqmh.service.IAdminService;
import com.yoosal.zqmh.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 秦明涛 on 2016/1/28.
 * Desc
 */
@Service("adminService")
public class AdminServiceImpl extends CommonServiceImpl<Admin> implements IAdminService {
    @Resource
    private AdminMapper adminMapper;

    public Admin selectByName(String name) {
        if (StringUtil.isNotEmpty(name)) {
            return adminMapper.selectByName(name);
        }
        return null;
    }

    public int deleteByPrimaryKey(Integer id) {
        if (id > 0) {
            return adminMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }

    public int insert(Admin record) {
        return adminMapper.insert(record);
    }

    public int insertSelective(Admin record) {
        return adminMapper.insertSelective(record);
    }

    public Admin selectByPrimaryKey(Integer id) {
        if (id != null && id > 0) {
            return adminMapper.selectByPrimaryKey(id);
        }
        return null;
    }

    public int updateByPrimaryKeySelective(Admin record) {
        if (record != null && record.getId() > 0) {
            return adminMapper.updateByPrimaryKeySelective(record);
        }
        return 0;
    }

    public int updateByPrimaryKey(Admin record) {
        if (record != null && record.getId() > 0) {
            return adminMapper.updateByPrimaryKey(record);
        }
        return 0;
    }

    public List<Admin> queryAll(String orderBy, int start, int limit) {
        if (StringUtil.isNotEmpty(orderBy) && StringUtil.isNotEmpty(StringUtil.transactSQLInjection(orderBy))) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("orderBy",orderBy);
            param.put("start",start);
            param.put("limit",limit);
            return adminMapper.queryAll(param);
        }
        return null;
    }

    public int queryAllCount() {
        return adminMapper.queryAllCount();
    }

    @Override
    protected CommonDao<Admin> getDao() {
        return adminMapper;
    }
}
