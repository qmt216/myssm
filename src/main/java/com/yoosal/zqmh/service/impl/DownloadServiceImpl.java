package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.dao.DownloadMapper;
import com.yoosal.zqmh.pojo.Download;
import com.yoosal.zqmh.service.IDownloadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by qinmingtao on 2016/8/19.
 * Desc 文件下载
 */
@Service
public class DownloadServiceImpl extends CommonServiceImpl<Download> implements IDownloadService{
    @Resource
    DownloadMapper downloadMapper;
    @Override
    protected CommonDao<Download> getDao() {
        return downloadMapper;
    }
}
