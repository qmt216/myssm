//package com.yoosal.zqmh.control.background;
//
//import com.yoosal.zqmh.control.FileUtil;
//import com.yoosal.zqmh.control.MessageUtil;
//import com.yoosal.zqmh.modules.ZeroModules;
//import com.yoosal.zqmh.modules.resolve.ResolveBean;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletRequest;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by ibm on 14-1-21.
// */
//@Controller
//@RequestMapping("/bg/resolve")
//@SuppressWarnings("unchecked")
//public class ResolveControl {
//    public static final String navTabId = "Resolve_index";
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
//        List<ResolveBean> beanList;
//        long count = 0;
//        if (StringUtils.isBlank(keyword)) {
//            beanList = ZeroModules.resolve.getList(ResolveBean.class, i_start, i_limit);
//            count = ZeroModules.resolve.getCount(ResolveBean.class);
//        } else {
//            ResolveBean bean = new ResolveBean();
//            bean.setName(keyword);
//            beanList = ZeroModules.resolve.getList(bean, i_start, i_limit);
//            count = ZeroModules.resolve.getCount(bean);
//        }
//        modelMap.put("totalCount", count);
//        modelMap.put("numPerPage", i_limit);
//        modelMap.put("currentPage", pageNum);
//        modelMap.put("list", beanList);
//        return "bg/resolve/index";
//    }
//
//    @RequestMapping(value = "/add.html")
//    public String add() {
//        return "bg/resolve/add";
//    }
//
//    @RequestMapping(value = "/insert.html")
//    @ResponseBody
//    public String insert(HttpServletRequest request, @RequestParam MultipartFile img, String name, String summary, String introduce, String sort) throws Exception {
//        ResolveBean bean = new ResolveBean();
//        if (StringUtils.isBlank(name) || StringUtils.isBlank(summary) || StringUtils.isBlank(introduce)) {
//            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
//        }
//        bean.setName(name);
//        bean.setSummary(summary);
//        bean.setIntroduce(introduce);
//        bean.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        bean.setSort(StringUtils.isBlank(sort) ? "0" : sort);
//        // 判断enctype属性是否为multipart/form-data
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//        // Create a factory for disk-based file items
//        if (isMultipart) {
//            String result = FileUtil.uploadFile(request, img, navTabId);
//            if (result.startsWith("/upload/")) {
//                bean.setImg(result);
//            } else {
//                return result;
//            }
//        }
//
//        ZeroModules.resolve.insert(bean);
//        return MessageUtil.successMessage(MessageUtil.Message.insertSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
//    }
//
//    @RequestMapping(value = "/edit.html")
//    public String edit(String id, ModelMap modelMap) {
//        ResolveBean bean = (ResolveBean) ZeroModules.resolve.getById(ResolveBean.class, id);
//        modelMap.put("bean", bean);
//        return "bg/resolve/edit";
//    }
//
//    @RequestMapping(value = "/update.html")
//    @ResponseBody
//    public String update(HttpServletRequest request, @RequestParam MultipartFile img, String id, String name, String summary, String introduce, String sort) throws Exception {
//        ResolveBean bean = new ResolveBean();
//        if (StringUtils.isBlank(id)) {
//            return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
//        }
//        if (StringUtils.isBlank(name) || StringUtils.isBlank(summary) || StringUtils.isBlank(introduce)) {
//            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
//        }
//        bean.setId(id);
//        bean.setName(name);
//        bean.setSummary(summary);
//        bean.setIntroduce(introduce);
//        bean.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        bean.setSort(StringUtils.isBlank(sort) ? "0" : sort);
//        // 判断enctype属性是否为multipart/form-data
//        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//        // Create a factory for disk-based file items
//        if (isMultipart) {
//            String result = FileUtil.uploadFile(request, img, navTabId);
//            if (result.startsWith("/upload/")) {
//                bean.setImg(result);
//            } else {
//                return result;
//            }
//        }
//
//        ZeroModules.resolve.update(bean);
//        return MessageUtil.successMessage(MessageUtil.Message.editSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
//    }
//
//    @RequestMapping(value = "/delete.html")
//    @ResponseBody
//    public String delete(String id) {
//        if (StringUtils.isBlank(id)) {
//            return MessageUtil.errorMessage("数据传输失败，请刷新后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
//        }
//        ZeroModules.resolve.deleteById(ResolveBean.class, id);
//        return MessageUtil.successMessage(MessageUtil.Message.dropSuccess, navTabId, MessageUtil.CallBackType.refreshCurrent);
//    }
//}
