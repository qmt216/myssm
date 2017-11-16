package com.yoosal.zqmh.control.background;

import com.alibaba.fastjson.JSONObject;
import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.control.Common;
import com.yoosal.zqmh.control.FileUtil;
import com.yoosal.zqmh.control.MessageUtil;
import com.yoosal.zqmh.pojo.*;
import com.yoosal.zqmh.util.StringUtil;
import com.yoosal.zqmh.util.search.ElasticSearchHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: ibm
 * Date: 14-1-6
 * Time: 上午12:00
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/bg/information")
public class InformationControl {
    static final String navTabId = "Information_index";
    static final String navTabId1 = "Information_all";
    private static Logger logger = Logger.getLogger(InformationControl.class);

    @RequestMapping(value = "/index.html")
    public String getIndex(ModelMap modelMap) {
        modelMap.put("headCates", Common.getNav());
        return "/bg/information/index";
    }

    @RequestMapping(value = "/list.html")
    public String getInformationList(String cateId, String keyword, String pageNum, String limit, ModelMap modelMap) {
        processList(cateId, keyword, pageNum, limit, modelMap);
        return "/bg/information/list";
    }

    @RequestMapping(value = "/all.html")
    @SuppressWarnings("unchecked")
    public String getAllInformation(String keyword, String pageNum, String limit, ModelMap modelMap) {
        processList("", keyword, pageNum, limit, modelMap);
        List<InformationCate> cateList = GlobalModule.informationCateService.queryAll("id", 0, 1000);
        if (cateList != null && cateList.size() > 0) {
            List<Information> list = (List<Information>) modelMap.get("list");
            if (list != null && list.size() > 0) {
                for (Information information : list) {
                    for (InformationCate cate : cateList) {
                        if (cate.getId().equals(information.getCateId())) {
                            information.setCateName(cate.getName());
                            break;
                        }
                    }
                }
            }
        }
        return "/bg/information/all";
    }

    private void processList(String cateId, String keyword, String pageNum, String limit, ModelMap modelMap) {
        int i_pageNum = 1, i_limit = 20;
        if (StringUtils.isNotBlank(pageNum)) {
            i_pageNum = Integer.parseInt(pageNum);
        }
        if (StringUtils.isNotBlank(limit)) {
            i_limit = Integer.parseInt(limit);
        }
        int i_start = (i_pageNum - 1) * i_limit;
        String condition = "1=1";
        if (StringUtil.isNotEmpty(cateId)) {
            condition += " and cate_id=" + cateId;
        }
        if (StringUtils.isNotBlank(keyword)) {
            condition += " and name like '%" + keyword + "%'";
        }
        List<Information> list = GlobalModule.informationService.selectByExample(condition, " id desc", i_start, i_limit);
        long count = GlobalModule.informationService.countByExample(condition);
        modelMap.put("totalCount", count);
        modelMap.put("numPerPage", i_limit);
        modelMap.put("currentPage", pageNum);
        modelMap.put("list", list);
        modelMap.put("cateId", cateId);
    }

    @RequestMapping(value = "/add.html")
    public String add(String cateId, ModelMap modelMap) {
        modelMap.put("cateId", cateId);
        return "/bg/information/add";
    }

    @RequestMapping(value = "/edit.html")
    public String update(String id, String all, ModelMap modelMap) {
        Information bean = GlobalModule.informationService.selectByPrimaryKey(StringUtil.toInt(id, -1));
        modelMap.put("bean", bean);
        if (StringUtil.isNotEmpty(all)) {
            modelMap.put("all", all);
        }
        return "/bg/information/edit";
    }

    @RequestMapping(value = "/insert.html")
    @ResponseBody
    public JSONObject insert(String title, String sort, String author, String fr, String nameStyle, String content, String cateId) throws Exception {
        Information bean = new Information();
        if (StringUtils.isBlank(title) || StringUtils.isBlank(content) || StringUtils.isBlank(author) || StringUtils.isBlank(cateId)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setName(title);
        bean.setCateId(StringUtil.toInt(cateId));
        bean.setSort(StringUtil.toInt(sort, 99));
        bean.setAuthor(author);
        bean.setFr(fr);
        bean.setNameStyle(StringUtil.toInt(nameStyle, 0));
        bean.setContent(content);
        GlobalModule.informationService.insert(bean);
        try {
            // 创建索引
            if (ElasticSearchHandler.createIndex(bean, bean.getId())) {
                logger.error("添加索引成功：" + bean.getId());
            } else {
                logger.error("添加索引失败" + bean.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject result = MessageUtil.successMessage(MessageUtil.Message.insertSuccess, "", MessageUtil.CallBackType.closeCurrent);
        result.put("rel", "jbsxBox");
        return result;
    }

    @RequestMapping(value = "/update.html")
    @ResponseBody
    public JSONObject update(String id, String title, String sort, String author, String fr, String nameStyle, String content, String all) throws Exception {
        if (StringUtils.isBlank(id)) {
            if (StringUtil.isNotEmpty(all)) {
                return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId1, MessageUtil.CallBackType.refreshCurrent);
            } else {
                return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
            }

        }
        Information bean = GlobalModule.informationService.selectByPrimaryKey(StringUtil.toInt(id));
        if (StringUtils.isBlank(title) || StringUtils.isBlank(content) || StringUtils.isBlank(author)) {
            if (StringUtil.isNotEmpty(all)) {
                return MessageUtil.errorMessage("请将必填项填写完整！", navTabId1, MessageUtil.CallBackType.refreshCurrent);
            } else {
                return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
            }
        }
        bean.setName(title);
        bean.setSort(StringUtil.toInt(sort, 99));
        bean.setAuthor(author);
        bean.setFr(fr);
        bean.setNameStyle(StringUtil.toInt(nameStyle, 0));
        bean.setContent(content);
        GlobalModule.informationService.updateByPrimaryKey(bean);
        try {
            //更新索引
            if (ElasticSearchHandler.updateIndex(bean, bean.getId())) {
                logger.error("添加索引成功：" + bean.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject result;
        if (StringUtil.isEmpty(all)) {
            result = MessageUtil.successMessage(MessageUtil.Message.editSuccess, "", MessageUtil.CallBackType.closeCurrent);
            result.put("rel", "jbsxBox");
        } else {
            result = MessageUtil.successMessage(MessageUtil.Message.editSuccess, navTabId1, MessageUtil.CallBackType.closeCurrent);
        }
        return result;
    }

    @RequestMapping(value = "/delete.html")
    @ResponseBody
    public JSONObject delete(String id, String all) {
        if (StringUtils.isBlank(id)) {
            JSONObject result;
            if (StringUtil.isEmpty(all)) {
                result = MessageUtil.errorMessage("数据传输失败，请刷新后重试！", "", MessageUtil.CallBackType.refreshCurrent);
                result.put("rel", "jbsxBox");
            } else {
                return MessageUtil.errorMessage("数据传输失败，请刷新后重试！", navTabId1, MessageUtil.CallBackType.refreshCurrent);
            }
            return result;
        }
        GlobalModule.informationService.deleteByPrimaryKey(StringUtil.toInt(id));
        try {
            //删除索引
            ElasticSearchHandler.delIndex(new Information(), StringUtil.toInt(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject result;
        if (StringUtil.isEmpty(all)) {
            result = MessageUtil.successMessage(MessageUtil.Message.dropSuccess, "", MessageUtil.CallBackType.refreshCurrent);
            result.put("rel", "jbsxBox");
        } else {
            result = MessageUtil.successMessage(MessageUtil.Message.dropSuccess, navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        return result;
    }

    @RequestMapping(value = "/uploadImg.html")
    @ResponseBody
    public JSONObject uploadImg(HttpServletRequest request) throws IOException {
        String ymd = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if ("application/octet-stream".equals(request.getContentType())) {
            try {
                String ex = request.getHeader("Content-Disposition");
                int iFindStart = ex.indexOf("filename=\"") + 10;
                int iFindEnd = ex.indexOf("\"", iFindStart);
                String sFileName = ex.substring(iFindStart, iFindEnd);
                int i = request.getContentLength();
                byte[] buffer = new byte[i];
                ServletInputStream inputStream = request.getInputStream();

                int filePath;
                for (int j = 0; j < i; j += filePath) {
                    filePath = inputStream.read(buffer, j, i - j);
                }

                JSONObject result = new JSONObject();
                if (buffer.length == 0) {
                    result.put("err", "上传文件不能为空");
                    result.put("msg", "");
                    return result;
                }

                if ((long) buffer.length > 2048000L) {
                    result.put("err", "上传文件不能为空");
                    result.put("msg", "");
                    return result;
                }
                String fileExt = sFileName.substring(sFileName.lastIndexOf(".") + 1).toLowerCase();
                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;

                String uploadPath = FileUtil.getSavePath(request) + newFileName;
                String readPath = "/upload/" + ymd + "/" + newFileName;
                BufferedOutputStream fileOut = new BufferedOutputStream(new FileOutputStream(uploadPath, true));
                fileOut.write(buffer);
                fileOut.close();
                inputStream.close();
                result.put("err", "");
                result.put("msg", request.getContextPath() + readPath);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @RequestMapping(value = "/initIndex.html")
    @ResponseBody
    public JSONObject initIndex() {
        List<Information> informationList = GlobalModule.informationService.queryAll("id desc", 0, 10000);
        if (informationList != null && informationList.size() > 0) {
            ElasticSearchHandler.getInstance(false);
            for (Information information : informationList) {
                ElasticSearchHandler.createIndex(information, information.getId());
            }
            ElasticSearchHandler.close();
        }
        return MessageUtil.successMessage("添加成功", "", MessageUtil.CallBackType.refreshCurrent);
    }

}
