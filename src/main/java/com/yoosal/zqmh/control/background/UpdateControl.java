//package com.yoosal.zqmh.control.background;
//
//import com.yoosal.zqmh.control.MessageUtil;
//import com.yoosal.zqmh.modules.ZeroModules;
//import com.yoosal.zqmh.modules.update.UpdateBean;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by ibm on 14-1-21.
// */
//@Controller
//@RequestMapping("/bg/update")
//public class UpdateControl {
//    public static final String navTabId = "Update_Log_index";
//
//    @RequestMapping(value = "/index.html")
//    public String index(String keyword, String pageNum, String limit, ModelMap modelMap) {
//        int i_pageNum = 1, i_limit = 10, i_start = 0;
//        if (StringUtils.isNotBlank(pageNum)) {
//            i_pageNum = Integer.parseInt(pageNum);
//        }
//        if (StringUtils.isNotBlank(limit)) {
//            i_limit = Integer.parseInt(limit);
//        }
//        i_start = (i_pageNum - 1) * i_limit;
//        List<UpdateBean> beanList;
//        long count = 0;
//        if (StringUtils.isBlank(keyword)) {
//            beanList = ZeroModules.update.getList(UpdateBean.class, i_start, i_limit);
//            count = ZeroModules.update.getCount(UpdateBean.class);
//        } else {
//            UpdateBean bean = new UpdateBean();
//            bean.setName(keyword);
//            beanList = ZeroModules.update.getList(bean, i_start, i_limit);
//            count = ZeroModules.update.getCount(bean);
//        }
//        modelMap.put("totalCount", count);
//        modelMap.put("numPerPage", i_limit);
//        modelMap.put("currentPage", pageNum);
//        modelMap.put("list", beanList);
//        return "bg/update/index";
//    }
//
//
//    @RequestMapping(value = "/add.html")
//    public String add() {
//        return "bg/update/add";
//    }
//
//    @RequestMapping(value = "/insert.html")
//    @ResponseBody
//    public String insert(String name, String updateTime, String summary, String introduce, String sort) throws Exception {
//        UpdateBean bean = new UpdateBean();
//        if (StringUtils.isBlank(name) || StringUtils.isBlank(summary) || StringUtils.isBlank(introduce) || StringUtils.isBlank(updateTime)) {
//            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
//        }
//        bean.setName(name);
//        bean.setUpdateTime(updateTime);
//        bean.setSummary(summary);
//        bean.setIntroduce(introduce);
//        bean.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        bean.setSort(StringUtils.isBlank(sort) ? "0" : sort);
//
//        ZeroModules.update.insert(bean);
//        return MessageUtil.successMessage(MessageUtil.Message.insertSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
//    }
//
//    @RequestMapping(value = "/edit.html")
//    public String edit(String id, ModelMap modelMap) {
//        UpdateBean bean = (UpdateBean) ZeroModules.update.getById(UpdateBean.class, id);
//        modelMap.put("bean", bean);
//        return "bg/update/edit";
//    }
//
//    @RequestMapping(value = "/update.html")
//    @ResponseBody
//    public String update(String id, String name, String updateTime, String summary, String introduce, String sort) throws Exception {
//        UpdateBean bean = new UpdateBean();
//        if (StringUtils.isBlank(id)) {
//            return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
//        }
//        if (StringUtils.isBlank(name) || StringUtils.isBlank(summary) || StringUtils.isBlank(introduce)) {
//            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
//        }
//        bean.setId(id);
//        bean.setName(name);
//        bean.setUpdateTime(updateTime);
//        bean.setSummary(summary);
//        bean.setIntroduce(introduce);
//        bean.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        bean.setSort(StringUtils.isBlank(sort) ? "0" : sort);
//
//        ZeroModules.update.update(bean);
//        return MessageUtil.successMessage(MessageUtil.Message.editSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
//    }
//
//    @RequestMapping(value = "/delete.html")
//    @ResponseBody
//    public String delete(String id) {
//        if (StringUtils.isBlank(id)) {
//            return MessageUtil.errorMessage("数据传输失败，请刷新后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
//        }
//        ZeroModules.update.deleteById(UpdateBean.class, id);
//        return MessageUtil.successMessage(MessageUtil.Message.dropSuccess, navTabId, MessageUtil.CallBackType.refreshCurrent);
//    }
//}
