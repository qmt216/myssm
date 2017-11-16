package com.yoosal.zqmh.control.background;

import com.alibaba.fastjson.JSONObject;
import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.control.MessageUtil;
import com.yoosal.zqmh.pojo.Link;
import com.yoosal.zqmh.pojo.User;
import com.yoosal.zqmh.util.MD5;
import com.yoosal.zqmh.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ibm
 * Date: 14-1-6
 * Time: 上午12:00
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/bg/user")
@SuppressWarnings("unchecked")
public class UserControl {
    static final String navTabId = "User_index";

    @RequestMapping(value = "/index.html")
    public String getProducts(String keyword, String pageNum, String limit, ModelMap modelMap) {
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
        List<User> list = GlobalModule.userService.selectByExample(condition, "id desc", i_start, i_limit);
        long count = GlobalModule.userService.countByExample(condition);


        modelMap.put("totalCount", count);
        modelMap.put("numPerPage", i_limit);
        modelMap.put("currentPage", pageNum);
        modelMap.put("list", list);
        return "/bg/user/index";
    }

    @RequestMapping(value = "/insert.html")
    @ResponseBody
    public JSONObject insert(String name, String password, String rePassword, String remark, String adNum) throws Exception {
        User bean = new User();
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password) || StringUtils.isBlank(rePassword)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        if (!password.equals(rePassword)) {
            return MessageUtil.errorMessage("您两次输入的密码不一致！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setName(name);
        bean.setPassword(MD5.getMD5ofString(password));
        bean.setRemark(remark);
        bean.setEditTime(new Date());
        bean.setAdNum(StringUtil.toInt(adNum, 0));
        bean.setType(0);
        if (GlobalModule.userService.insertSelective(bean) > 0) {
            return MessageUtil.successMessage(MessageUtil.Message.insertSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
        } else {
            return MessageUtil.errorMessage(MessageUtil.Message.insertError, navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
    }

    @RequestMapping(value = "/add.html")
    public String add() {
        return "/bg/user/add";
    }

    @RequestMapping(value = "/edit.html")
    public String edit(String id, ModelMap modelMap) {
        User bean = GlobalModule.userService.selectByPrimaryKey(StringUtil.toInt(id));
        modelMap.put("bean", bean);
        return "/bg/user/edit";
    }

    @RequestMapping(value = "/update.html")
    @ResponseBody
    public JSONObject update(String id, String password, String rePassword, String remark, String adNum) throws Exception {
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        User bean = GlobalModule.userService.selectByPrimaryKey(StringUtil.toInt(id));
        if (bean == null) {
            return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        if (StringUtils.isBlank(remark) || StringUtils.isBlank(adNum)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        if (StringUtil.isNotEmpty(password)) {
            if (!password.equals(rePassword)) {
                return MessageUtil.errorMessage("您两次输入的密码不一致！", navTabId, MessageUtil.CallBackType.refreshCurrent);
            }
            bean.setPassword(MD5.getMD5ofString(password));
        }
        bean.setRemark(remark);
        bean.setAdNum(StringUtil.toInt(adNum, 0));
        bean.setEditTime(new Date());
        if (GlobalModule.userService.updateByPrimaryKey(bean) > 0) {
            return MessageUtil.successMessage(MessageUtil.Message.editSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
        } else {
            return MessageUtil.errorMessage(MessageUtil.Message.editError, navTabId, MessageUtil.CallBackType.refreshCurrent);
        }

    }

    @RequestMapping(value = "/delete.html")
    @ResponseBody
    public JSONObject delete(String id) {
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请刷新后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        if (GlobalModule.userService.deleteByPrimaryKey(StringUtil.toInt(id)) > 0) {
            return MessageUtil.successMessage(MessageUtil.Message.dropSuccess, navTabId, MessageUtil.CallBackType.refreshCurrent);
        } else {
            return MessageUtil.errorMessage(MessageUtil.Message.dropError, navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
    }
}
