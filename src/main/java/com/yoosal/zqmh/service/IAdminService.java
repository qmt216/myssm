package com.yoosal.zqmh.service;

import com.yoosal.zqmh.pojo.Admin;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 秦明涛 on 2016/1/28.
 * Desc
 */
public interface IAdminService extends ICommonService<Admin> {
    Admin selectByName(@Param(value = "name") String name);
}
