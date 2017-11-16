package com.yoosal.zqmh.control.background;

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
@RequestMapping("/bg/adv")
public class AdvControl {
    static final String navTabId = "Adv_index";
    static final String MainCode = "big_pic_news";

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
        List<Adv> advBeanList = GlobalModule.advService.selectByExample(condition, "create_time desc", i_start, i_limit);
        long count = GlobalModule.advService.countByExample(condition);

        modelMap.put("totalCount", count);
        modelMap.put("numPerPage", i_limit);
        modelMap.put("currentPage", pageNum);
        modelMap.put("list", advBeanList);
        return "/bg/adv/index";
    }

    @RequestMapping(value = "/add.html")
    public String add(String positionId, ModelMap modelMap) {
        //广告位置
        if (StringUtil.isEmpty(positionId)) {
            List<AdvPosition> advPositionList = GlobalModule.advPositionService.queryAll("id", 0, 100);
            List<AdvPosition> temp = new ArrayList<AdvPosition>();
            for (AdvPosition item : advPositionList) {
                if (item.getEnable() == 1) {
                    temp.add(item);
                }
            }
            modelMap.put("advPositionList", temp);
        } else {
            modelMap.put("positionId", positionId);
        }
        return "/bg/adv/add";
    }

    @RequestMapping(value = "/edit.html")
    public String update(String id, String positionId, ModelMap modelMap) {
        Adv advBean = GlobalModule.advService.selectByPrimaryKey(StringUtil.toInt(id, -1));
        modelMap.put("bean", advBean);
        //广告位置
        if (StringUtil.isEmpty(positionId)) {
            List<AdvPosition> advPositionList = GlobalModule.advPositionService.queryAll("id", 0, 100);
            List<AdvPosition> temp = new ArrayList<AdvPosition>();
            for (AdvPosition item : advPositionList) {
                if (item.getEnable() == 1) {
                    temp.add(item);
                }
            }
            modelMap.put("advPositionList", temp);
        } else {
            modelMap.put("positionId", positionId);
        }
        return "/bg/adv/edit";
    }

    @RequestMapping(value = "/insert.html")
    @ResponseBody
    public JSONObject insert(HttpServletRequest request, @RequestParam MultipartFile img, String title, String url, String enable, String positionId, String sort) throws Exception {
        Adv bean = new Adv();
        if (StringUtils.isBlank(title) || StringUtils.isBlank(url) || StringUtils.isBlank(enable) || StringUtils.isBlank(positionId)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setName(title);
        bean.setUrl(url);
        bean.setEnable(StringUtil.toInt(enable, 0));
        bean.setPositionId(StringUtil.toInt(positionId, -1));
        bean.setCreateTime(new Date());
        bean.setSort(StringUtil.toInt(sort));
        // 判断enctype属性是否为multipart/form-data
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        // Create a factory for disk-based file items
        if (isMultipart) {
            String result = FileUtil.uploadFile(request, img, navTabId);
            if (result.startsWith("/upload/")) {
                bean.setPic(result);
            } else {
                return JSONObject.parseObject(result);
            }
        }
        GlobalModule.advService.insert(bean);
        return MessageUtil.successMessage(MessageUtil.Message.insertSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
    }

    @RequestMapping(value = "/update.html")
    @ResponseBody
    public JSONObject update(HttpServletRequest request, @RequestParam MultipartFile img, String id, String title, String url, String enable, String positionId, String sort) throws Exception {
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        Adv bean = GlobalModule.advService.selectByPrimaryKey(StringUtil.toInt(id));
        if (bean == null || StringUtils.isBlank(title) || StringUtils.isBlank(url) || StringUtils.isBlank(enable) || StringUtils.isBlank(positionId)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setName(title);
        bean.setUrl(url);
        bean.setEnable(StringUtil.toInt(enable, 0));
        bean.setPositionId(StringUtil.toInt(positionId, -1));
        bean.setCreateTime(new Date());
        bean.setSort(StringUtil.toInt(sort));
        // 判断enctype属性是否为multipart/form-data
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (isMultipart && img!=null) {
            String result = FileUtil.uploadFile(request, img, navTabId);
            if (result.startsWith("/upload/")) {
                bean.setPic(result);
            } else {
                return JSONObject.parseObject(result);
            }
        }
        GlobalModule.advService.updateByPrimaryKey(bean);
        return MessageUtil.successMessage(MessageUtil.Message.editSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
    }

    @RequestMapping(value = "/delete.html")
    @ResponseBody
    public JSONObject delete(String id) {
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请刷新后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        GlobalModule.advService.deleteByPrimaryKey(StringUtil.toInt(id));
        return MessageUtil.successMessage(MessageUtil.Message.dropSuccess, navTabId, MessageUtil.CallBackType.refreshCurrent);
    }

    @RequestMapping(value = "/mainPic.html")
    public String getMainPic(String keyword, String pageNum, String limit, ModelMap modelMap) {
        List<AdvPosition> positionList = GlobalModule.advPositionService.selectByExample("code='" + MainCode + "'", "id", 0, 1);
        int positionId = 0;
        if (positionList != null && positionList.size() > 0) {
            positionId = positionList.get(0).getId();
            modelMap.put("positionId", positionId);
        }
        int i_pageNum = 1, i_limit = 10, i_start = 0;
        if (StringUtils.isNotBlank(pageNum)) {
            i_pageNum = Integer.parseInt(pageNum);
        }
        if (StringUtils.isNotBlank(limit)) {
            i_limit = Integer.parseInt(limit);
        }
        i_start = (i_pageNum - 1) * i_limit;

        List<Adv> advBeanList;
        long count;
        if (StringUtils.isNotBlank(keyword)) {
            advBeanList = GlobalModule.advService.selectByExample("position_id=" + positionId, "create_time desc", i_start, i_limit);
            count = GlobalModule.advService.countByExample("position_id=" + positionId);
        } else {
            advBeanList = GlobalModule.advService.selectByExample("position_id=" + positionId, "create_time desc", i_start, i_limit);
            count = GlobalModule.advService.countByExample("position_id=" + positionId);
        }

        modelMap.put("totalCount", count);
        modelMap.put("numPerPage", i_limit);
        modelMap.put("currentPage", pageNum);
        modelMap.put("list", advBeanList);
        return "/bg/adv/index";
    }
}
