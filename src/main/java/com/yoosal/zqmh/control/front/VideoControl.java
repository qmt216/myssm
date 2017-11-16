package com.yoosal.zqmh.control.front;

import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.control.Common;
import com.yoosal.zqmh.pojo.*;
import com.yoosal.zqmh.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by qinmingtao on 2016/8/31.
 * Desc 视频展示
 */
@Controller(value = "frontVideoControl")
public class VideoControl {

    /**
     * 视频展示列表
     *
     * @param pageNum 页码
     */
    @RequestMapping(value = "/video/list.html")
    private String list(HttpServletRequest request, String pageNum, ModelMap modelMap) {
        Common.top(request, modelMap);
        Common.bottom(request, modelMap);

        int i_pageNum = 1, i_limit = 20;
        if (StringUtils.isNotBlank(pageNum)) {
            i_pageNum = Integer.parseInt(pageNum);
        }
        int i_start = (i_pageNum - 1) * i_limit;

        List<Video> list = GlobalModule.videoService.selectByExample("enable=1", "edit_time desc", i_start, i_limit);
        long count = GlobalModule.videoService.countByExample("enable=1");

        modelMap.put("pageCount", (count / i_limit + (count % i_limit == 0 ? 0 : 1)));
        modelMap.put("currentPage", i_pageNum);
        modelMap.put("beanList", list);

        return "/front/video/list";
    }

    /**
     * 新闻详情
     *
     * @param id ID
     */

    @RequestMapping(value = "/video/{id}.html")
    public String viewProduct(@PathVariable String id, ModelMap modelMap, HttpServletRequest request) {
        if (StringUtils.isBlank(id) || StringUtil.toInt(id) < 0) {
            return "/front/error/404";
        }
        Common.top(request, modelMap);
        Common.bottom(request, modelMap);
        //获取视频详情
        Video video = GlobalModule.videoService.selectByPrimaryKey(StringUtil.toInt(id));
        if (video != null) {
            Video temp = new Video();
            temp.setId(video.getId());
            temp.setView(video.getView() + 1);
            GlobalModule.videoService.updateByPrimaryKeySelective(temp);
        }
        modelMap.put("video", video);
        return "/front/video/content";
    }

}
