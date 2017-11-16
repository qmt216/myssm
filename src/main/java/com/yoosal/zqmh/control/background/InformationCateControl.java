package com.yoosal.zqmh.control.background;

import com.alibaba.fastjson.JSONObject;
import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.control.MessageUtil;
import com.yoosal.zqmh.pojo.Friend;
import com.yoosal.zqmh.pojo.InformationCate;
import com.yoosal.zqmh.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ibm
 * Date: 14-1-6
 * Time: 上午12:00
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/bg/informationCate")
public class InformationCateControl {
    static final String navTabId = "Information_Cate_ind";

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
        List<InformationCate> list = GlobalModule.informationCateService.selectByExample(condition, "id desc", i_start, i_limit);
        long count = GlobalModule.informationCateService.countByExample(condition);

        List<InformationCate> allList = GlobalModule.informationCateService.queryAll("id desc", 0, 1000);
        Map<Integer, String> allMap = new HashMap<Integer, String>();
        for (InformationCate cate : allList) {
            if (cate.getParentId() == 0) {
                allMap.put(cate.getId(), cate.getName());
            }
        }
        for (InformationCate cate : list) {
            if (cate.getParentId() == 0) {
                cate.setParent("顶级分类");
            } else if (allMap.containsKey(cate.getParentId())) {
                cate.setParent(allMap.get(cate.getParentId()));
            } else {
                cate.setParent("未知分类");
            }
        }
        modelMap.put("totalCount", count);
        modelMap.put("numPerPage", i_limit);
        modelMap.put("currentPage", pageNum);
        modelMap.put("list", list);
        return "/bg/informationCate/index";
    }

    @RequestMapping(value = "/add.html")
    public String add(ModelMap modelMap) {
        List<InformationCate> cateList = GlobalModule.informationCateService.queryAll("id", 0, 100);
        if (cateList != null && cateList.size() > 0) {
            List<InformationCate> tempList = new ArrayList<InformationCate>();
            for (InformationCate cate : cateList) {
                if (cate.getParentId() == 0) {
                    tempList.add(cate);
                }
            }
            modelMap.put("cateList", tempList);
        }
        return "/bg/informationCate/add";
    }

    @RequestMapping(value = "/edit.html")
    public String update(String id, ModelMap modelMap) {
        InformationCate advBean = GlobalModule.informationCateService.selectByPrimaryKey(StringUtil.toInt(id));
        modelMap.put("bean", advBean);

        List<InformationCate> cateList = GlobalModule.informationCateService.queryAll("id", 0, 100);
        if (cateList != null && cateList.size() > 0) {
            List<InformationCate> tempList = new ArrayList<InformationCate>();
            for (InformationCate cate : cateList) {
                if (cate.getParentId() == 0) {
                    tempList.add(cate);
                }
            }
            modelMap.put("cateList", tempList);
        }
        return "/bg/informationCate/edit";
    }

    @RequestMapping(value = "/insert.html")
    @ResponseBody
    public JSONObject insert(String name, String order, String parentId, String enable, String url) throws Exception {
        InformationCate bean = new InformationCate();
        if (StringUtils.isBlank(name) || StringUtils.isBlank(order) || StringUtils.isBlank(parentId)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setName(name);
        bean.setOrder(StringUtil.toInt(order));
        bean.setParentId(StringUtil.toInt(parentId, 0));
        bean.setEnable(StringUtil.toInt(enable, 0));
        bean.setUrl(url);
        bean.setCreateTime(new Date(System.currentTimeMillis()));
        GlobalModule.informationCateService.insert(bean);
        return MessageUtil.successMessage(MessageUtil.Message.insertSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
    }

    @RequestMapping(value = "/update.html")
    @ResponseBody
    public JSONObject update(String id, String name, String order, String parentId, String enable, String url) throws Exception {
        InformationCate bean = new InformationCate();
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        if (StringUtils.isBlank(name) || StringUtils.isBlank(order) || StringUtils.isBlank(parentId)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setId(StringUtil.toInt(id));
        bean.setName(name);
        bean.setOrder(StringUtil.toInt(order));
        bean.setParentId(StringUtil.toInt(parentId));
        bean.setEnable(StringUtil.toInt(enable, 0));
        bean.setUrl(url);
        bean.setCreateTime(new Date(System.currentTimeMillis()));
        GlobalModule.informationCateService.updateByPrimaryKey(bean);
        return MessageUtil.successMessage(MessageUtil.Message.editSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
    }

    @RequestMapping(value = "/delete.html")
    @ResponseBody
    public JSONObject delete(String id) {
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请刷新后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        GlobalModule.informationCateService.deleteByPrimaryKey(StringUtil.toInt(id));
        return MessageUtil.successMessage(MessageUtil.Message.dropSuccess, navTabId, MessageUtil.CallBackType.refreshCurrent);
    }

}
