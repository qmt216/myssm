package com.yoosal.zqmh.control.background;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.control.FileUtil;
import com.yoosal.zqmh.control.MessageUtil;
import com.yoosal.zqmh.pojo.Adv;
import com.yoosal.zqmh.pojo.AdvPosition;
import com.yoosal.zqmh.pojo.Download;
import com.yoosal.zqmh.util.StringUtil;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
@RequestMapping("/bg/download")
public class DownloadControl {
    static final String navTabId = "Download_index";

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
        List<Download> beanList = GlobalModule.downloadService.selectByExample(condition, "id desc", i_start, i_limit);
        long count = GlobalModule.downloadService.countByExample(condition);

        modelMap.put("totalCount", count);
        modelMap.put("numPerPage", i_limit);
        modelMap.put("currentPage", pageNum);
        modelMap.put("list", beanList);
        return "/bg/download/index";
    }

    @RequestMapping(value = "/add.html")
    public String add(ModelMap modelMap) {
        return "/bg/download/add";
    }

    @RequestMapping(value = "/edit.html")
    public String update(String id, ModelMap modelMap) {
        Download bean = GlobalModule.downloadService.selectByPrimaryKey(StringUtil.toInt(id, -1));
        modelMap.put("bean", bean);
        return "/bg/download/edit";
    }

    @RequestMapping(value = "/insert.html")
    @ResponseBody
    public JSONObject insert(HttpServletRequest request, @RequestParam MultipartFile img, String title, String isLogin, String enable) throws Exception {
        Download bean = new Download();
        if (StringUtils.isBlank(title) || StringUtils.isBlank(enable)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setName(title);
        bean.setEnable(StringUtil.toInt(enable, 0));
        bean.setIsLogin(StringUtil.toInt(isLogin));
        // 判断enctype属性是否为multipart/form-data
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        // Create a factory for disk-based file items
        if (isMultipart) {
            bean.setFileName(img.getOriginalFilename());
            String result = FileUtil.uploadFile(request, img, navTabId);
            if (result.startsWith("/upload/")) {
                bean.setUrl(result);
            } else {
                return JSONObject.parseObject(result);
            }
        }
        GlobalModule.downloadService.insert(bean);
        return MessageUtil.successMessage(MessageUtil.Message.insertSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
    }

    @RequestMapping(value = "/update.html")
    @ResponseBody
    public JSONObject update(HttpServletRequest request, @RequestParam MultipartFile img, String id, String title, String enable, String isLogin) throws Exception {
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        Download bean = GlobalModule.downloadService.selectByPrimaryKey(StringUtil.toInt(id));
        if (bean == null || StringUtils.isBlank(title) || StringUtils.isBlank(enable)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setName(title);
        bean.setEnable(StringUtil.toInt(enable, 0));
        bean.setIsLogin(StringUtil.toInt(isLogin, 0));
        // 判断enctype属性是否为multipart/form-data
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart) {
            bean.setFileName(img.getName());
            String result = FileUtil.uploadFile(request, img, navTabId);
            if (result.startsWith("/upload/")) {
                bean.setUrl(result);
            } else {
                return JSONObject.parseObject(result);
            }
        }
        GlobalModule.downloadService.updateByPrimaryKey(bean);
        return MessageUtil.successMessage(MessageUtil.Message.editSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
    }

    @RequestMapping(value = "/delete.html")
    @ResponseBody
    public JSONObject delete(String id) {
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请刷新后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        GlobalModule.downloadService.deleteByPrimaryKey(StringUtil.toInt(id));
        return MessageUtil.successMessage(MessageUtil.Message.dropSuccess, navTabId, MessageUtil.CallBackType.refreshCurrent);
    }

}
