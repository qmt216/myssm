package com.yoosal.zqmh.service.impl;

import com.yoosal.zqmh.dao.CommonDao;
import com.yoosal.zqmh.dao.VideoMapper;
import com.yoosal.zqmh.pojo.Video;
import com.yoosal.zqmh.service.IVideoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by qinmingtao on 2017/2/3.
 * Desc
 */
@Service("videoService")
public class VideoServiceImpl extends CommonServiceImpl<Video> implements IVideoService {
    @Resource
    VideoMapper videoMapper;

    @Override
    protected CommonDao<Video> getDao() {
        return videoMapper;
    }
}
