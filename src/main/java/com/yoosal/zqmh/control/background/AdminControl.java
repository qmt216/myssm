package com.yoosal.zqmh.control.background;

import com.alibaba.fastjson.JSONObject;
import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.GlobalVariable;
import com.yoosal.zqmh.control.MessageUtil;
import com.yoosal.zqmh.pojo.Admin;
import com.yoosal.zqmh.pojo.Admin;
import com.yoosal.zqmh.pojo.AdvPosition;
import com.yoosal.zqmh.pojo.Menu;
import com.yoosal.zqmh.util.CookieUtil;
import com.yoosal.zqmh.util.MD5;
import com.yoosal.zqmh.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bg/admin")
public class AdminControl {
    static final String navTabId = "Admin_index";
    public static final String id = "id";

    @RequestMapping(value = "/login.html")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        Admin admin = GlobalModule.adminService.selectByName(username);
        if (admin != null && admin.getPassword().equals(MD5.getMD5ofString(password))) {
            CookieUtil.addCookie(id, admin.getId() + "", "", -1, "/", response);
            request.getSession().setAttribute(GlobalVariable.ADMIN_SESSION_KEY, admin);
            return "success";
        }
        return "error";
    }

    @RequestMapping(value = "/logout.html")
    public void logout(HttpServletResponse response, HttpServletRequest request) {
        try {
            CookieUtil.removeCookie(GlobalVariable.USER_COOKIE_KEY, request, response);
            request.getSession().removeAttribute(GlobalVariable.ADMIN_SESSION_KEY);
            response.sendRedirect("/bg/login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/changePwd.html", method = RequestMethod.GET)
    public String changePwd(HttpServletResponse response, HttpServletRequest request) {
        return "/bg/changePwd";
    }

    @RequestMapping(value = "/changePwd.html", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject changePwdAjax(HttpServletRequest request, String oldpassword, String password, String repassword) {
        Admin admin = (Admin) request.getSession().getAttribute(GlobalVariable.ADMIN_SESSION_KEY);
        if (admin == null) {
            return MessageUtil.errorMessage("修改失败，请重新登陆", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        if (!password.equals(repassword)) {
            return MessageUtil.errorMessage("您两次输入的新密码不一致", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        if (!admin.getPassword().equals(MD5.getMD5ofString(oldpassword))) {
            return MessageUtil.errorMessage("您输入的原密码不正确", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        admin = GlobalModule.adminService.selectByPrimaryKey(admin.getId());
        admin.setPassword(MD5.getMD5ofString(password));
        GlobalModule.adminService.updateByPrimaryKey(admin);
        return MessageUtil.successMessage(MessageUtil.Message.editSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
    }

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
        List<Admin> list = GlobalModule.adminService.selectByExample(condition, "id desc", i_start, i_limit);
        long count = GlobalModule.adminService.countByExample(condition);

        modelMap.put("totalCount", count);
        modelMap.put("numPerPage", i_limit);
        modelMap.put("currentPage", pageNum);
        modelMap.put("list", list);
        return "/bg/admin/index";
    }

    @RequestMapping(value = "/insert.html")
    @ResponseBody
    public JSONObject insert(String name, String password, String rePassword, String realName, String permission) throws Exception {
        Admin bean = new Admin();
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password) || StringUtils.isBlank(rePassword) || StringUtils.isBlank(realName)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        bean.setName(name);
        bean.setPassword(MD5.getMD5ofString(password));
        bean.setRealName(realName);
        bean.setEditTime(new Date());
        bean.setType(0);
        bean.setPermission(permission);
        if (GlobalModule.adminService.insertSelective(bean) > 0) {
            return MessageUtil.successMessage(MessageUtil.Message.insertSuccess, navTabId, MessageUtil.CallBackType.closeCurrent);
        } else {
            return MessageUtil.errorMessage(MessageUtil.Message.insertError, navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
    }

    @RequestMapping(value = "/add.html")
    @SuppressWarnings("unchecked")
    public String add(ModelMap modelMap) {
        List<Menu> menuList = GlobalModule.menuService.selectByExample("is_show=1", "`group` asc, sort desc", 0, 100);
        modelMap.put("menuList", menuList);
        return "/bg/admin/add";
    }

    @RequestMapping(value = "/edit.html")
    @SuppressWarnings("unchecked")
    public String edit(String id, ModelMap modelMap) {
        Admin bean = GlobalModule.adminService.selectByPrimaryKey(StringUtil.toInt(id));
        modelMap.put("bean", bean);
        List<Menu> menuList = GlobalModule.menuService.selectByExample("is_show=1", "`group` asc, sort desc", 0, 100);
        modelMap.put("menuList", menuList);
        return "/bg/admin/edit";
    }

    @RequestMapping(value = "/update.html")
    @ResponseBody
    public JSONObject update(String id, String password, String rePassword, String realName, String permission) throws Exception {
        if (StringUtils.isBlank(id)) {
            return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        Admin bean = GlobalModule.adminService.selectByPrimaryKey(StringUtil.toInt(id));
        if (bean == null) {
            return MessageUtil.errorMessage("数据传输失败，请稍后重试！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        if (StringUtils.isBlank(realName)) {
            return MessageUtil.errorMessage("请将必填项填写完整！", navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
        if (StringUtil.isNotEmpty(password)) {
            if (!password.equals(rePassword)) {
                return MessageUtil.errorMessage("您两次输入的密码不一致！", navTabId, MessageUtil.CallBackType.refreshCurrent);
            }
            bean.setPassword(MD5.getMD5ofString(password));
        }
        bean.setRealName(realName);
        bean.setEditTime(new Date());
        bean.setPermission(permission);
        if (GlobalModule.adminService.updateByPrimaryKey(bean) > 0) {
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
        if (GlobalModule.adminService.deleteByPrimaryKey(StringUtil.toInt(id)) > 0) {
            return MessageUtil.successMessage(MessageUtil.Message.dropSuccess, navTabId, MessageUtil.CallBackType.refreshCurrent);
        } else {
            return MessageUtil.errorMessage(MessageUtil.Message.dropError, navTabId, MessageUtil.CallBackType.refreshCurrent);
        }
    }
}
