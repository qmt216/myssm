package com.yoosal.zqmh.control.background;

import com.alibaba.fastjson.JSONObject;
import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.control.MessageUtil;
import com.yoosal.zqmh.pojo.Adv;
import com.yoosal.zqmh.pojo.Friend;
import com.yoosal.zqmh.pojo.Link;
import com.yoosal.zqmh.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ibm
 * Date: 14-1-6
 * Time: 上午12:00
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/bg/link")
public class LinkControl {
    static final String navTabId = "Link_index";

    @RequestMapping(value = "/index.html")
    public String getAdvList(String keyword, String pageNum, String limit, ModelMap modelMap) {
        int i_pageNum = 1, i_limit = 10, i_start = 0;
        if (StringUtils.isNotBlank(pageNum)) {
            i_pageNum = Integer.parseInt(pageNum);
        }
        if (StringUtils.isNotBlank(limit)) {
            i_limit = Integer.parseInt(limit);
        }
        i_start = (i_pageNum - 1) * i_limit;

        String condition = "1=1";
        if (StringUtils.isNotBlank(keyword)) {
            condition += " and name like '%" + keyword + "%'";
        }
        List<Link> beanList = GlobalModule.linkService.selectByExample(condition, "id desc", i_start, i_limit);
        long count = GlobalModule.linkService.countByExample(condition);

        modelMap.put("totalCount", count);
        modelMap.put("numPerPage", i_limit);
        modelMap.put("currentPage", pageNum);
        modelMap.put("list", beanList);
        return "/bg/link/index";
    }

    @RequestMapping(value = "/add.html")
    public String add(ModelMap modelMap) {
        return "/bg/link/add";
    }

    @RequestMapping(value = "/edit.html")
    public String update(String id, ModelMap modelMap) {
        Link bean = GlobalModule.linkService.selectByPrimaryKey(StringUtil.toInt(id, -1));
        modelMap.put("bean", bean);
        return "/bg/link/edit";
    }

    @RequestMapping(value = "/insert.html")
    @ResponseBody
    public JSONObject insert(HttpServletRequest request, String title, String url, String enable, String sort) throws Exception {
        Link bean = new Link();
        if (StringUtils.isBlank(title) || StringUtils.isBlank(url) || StringUtils.isBlank(enable)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setName(title);
        bean.setUrl(url);
        bean.setEnable(StringUtil.toInt(enable, 0));
        bean.setSort(StringUtil.toInt(sort));
        GlobalModule.linkService.insert(bean);
        return MessageUtil.successMessage(MessageUtil.Message.insertSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
    }

    @RequestMapping(value = "/update.html")
    @ResponseBody
    public JSONObject update(HttpServletRequest request, String id, String title, String url, String enable, String sort) throws Exception {
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        Link bean = GlobalModule.linkService.selectByPrimaryKey(StringUtil.toInt(id));
        if (bean == null || StringUtils.isBlank(title) || StringUtils.isBlank(url) || StringUtils.isBlank(enable)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setName(title);
        bean.setUrl(url);
        bean.setEnable(StringUtil.toInt(enable, 0));
        bean.setSort(StringUtil.toInt(sort, 99));
        GlobalModule.linkService.updateByPrimaryKey(bean);
        return MessageUtil.successMessage(MessageUtil.Message.editSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
    }

    @RequestMapping(value = "/delete.html")
    @ResponseBody
    public JSONObject delete(String id) {
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请刷新后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        GlobalModule.linkService.deleteByPrimaryKey(StringUtil.toInt(id));
        return MessageUtil.successMessage(MessageUtil.Message.dropSuccess, navTabId, MessageUtil.CallBackType.refreshCurrent);
    }

}
