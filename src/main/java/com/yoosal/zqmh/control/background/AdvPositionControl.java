package com.yoosal.zqmh.control.background;

import com.alibaba.fastjson.JSONObject;
import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.control.MessageUtil;
import com.yoosal.zqmh.pojo.Adv;
import com.yoosal.zqmh.pojo.AdvPosition;
import com.yoosal.zqmh.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ibm
 * Date: 14-1-6
 * Time: 上午12:00
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/bg/advPosition")
public class AdvPositionControl {
    static final String navTabId = "AdvPosition_index";

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/index.html")
    public String getAdvList(String keyword, String pageNum, String limit, ModelMap modelMap) {
        int i_pageNum = 1, i_limit = 20, i_start = 0;
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
        List<AdvPosition> beanList = GlobalModule.advPositionService.selectByExample(condition, "id desc", i_start, i_limit);
        long count = GlobalModule.advPositionService.countByExample(condition);

        modelMap.put("totalCount", count);
        modelMap.put("numPerPage", i_limit);
        modelMap.put("currentPage", pageNum);
        modelMap.put("list", beanList);
        return "/bg/advPosition/index";
    }

    @RequestMapping(value = "/add.html")
    public String add() {
        return "/bg/advPosition/add";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/edit.html")
    public String update(String id, ModelMap modelMap) {
        AdvPosition bean = GlobalModule.advPositionService.selectByPrimaryKey(StringUtil.toInt(id, -1));
        modelMap.put("bean", bean);
        return "/bg/advPosition/edit";
    }

    @RequestMapping(value = "/insert.html")
    @ResponseBody
    public JSONObject insert(String title, String code, String height, String width, String enable) throws Exception {
        AdvPosition bean = new AdvPosition();
        if (StringUtils.isBlank(title) || StringUtils.isBlank(code) || StringUtils.isBlank(height) || StringUtils.isBlank(width)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setName(title);
        bean.setCode(code);
        bean.setHeight(StringUtil.toInt(height));
        bean.setWidth(StringUtil.toInt(width));
        bean.setEnable(StringUtil.toInt(enable, 0));
        GlobalModule.advPositionService.insert(bean);
        return MessageUtil.successMessage(MessageUtil.Message.insertSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
    }

    @RequestMapping(value = "/update.html")
    @ResponseBody
    public JSONObject update(String id, String title, String code, String height, String width, String enable) throws Exception {
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        AdvPosition bean = GlobalModule.advPositionService.selectByPrimaryKey(StringUtil.toInt(id, -1));
        if (bean == null || StringUtils.isBlank(title) || StringUtils.isBlank(code) || StringUtils.isBlank(height) || StringUtils.isBlank(width)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setId(StringUtil.toInt(id));
        bean.setName(title);
        bean.setCode(code);
        bean.setHeight(StringUtil.toInt(height));
        bean.setWidth(StringUtil.toInt(width));
        bean.setEnable(StringUtil.toInt(enable, 0));
        GlobalModule.advPositionService.updateByPrimaryKey(bean);
        return MessageUtil.successMessage(MessageUtil.Message.editSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/delete.html")
    @ResponseBody
    public JSONObject delete(String id) {
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请刷新后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        GlobalModule.advPositionService.deleteByPrimaryKey(StringUtil.toInt(id));
        return MessageUtil.successMessage(MessageUtil.Message.dropSuccess, navTabId, MessageUtil.CallBackType.refreshCurrent);
    }

}
