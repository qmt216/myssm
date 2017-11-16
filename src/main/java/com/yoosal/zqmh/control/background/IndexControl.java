package com.yoosal.zqmh.control.background;

import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.GlobalVariable;
import com.yoosal.zqmh.pojo.Admin;
import com.yoosal.zqmh.pojo.Menu;
import com.yoosal.zqmh.pojo.SystemWithBLOBs;
import com.yoosal.zqmh.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: qinmingtao
 * Date: 13-12-31
 * Time: 上午12:39
 * To change this template use File | Settings | File Templates.
 */
@Controller("bgIndexControl")
public class IndexControl {
    @RequestMapping("/bg/menu.html")
    @SuppressWarnings("unchecked")
    public String menu(HttpServletRequest request, String g, ModelMap modelMap) {
        List<Menu> menuList = GlobalModule.menuService.selectByExample(" `group`=" + g, "sort asc,id", 0, 100);
        Admin admin = (Admin) request.getSession().getAttribute(GlobalVariable.ADMIN_SESSION_KEY);
        if (admin == null) {
            modelMap.put("menu", "");
        } else {
            if (!admin.getName().equals("admin")) {
                List<Menu> temp = new ArrayList<Menu>();
                for (Menu menu : menuList) {
                    if (StringUtil.isNotEmpty(admin.getPermission())) {
                        if (admin.getPermission().contains("," + menu.getId() + ",")) {
                            temp.add(menu);
                        }
                    }
                }
                menuList = temp;
            }
        }
        modelMap.put("menu", menuList);
        return "/bg/menu";
    }

    @RequestMapping(value = "/mobile/product/newInventoryInquiry.html", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    @SuppressWarnings("unchecked")
    public String loadInventoryInquiryInfo(ModelMap modelMap) {
        SystemWithBLOBs withBLOBs = GlobalModule.systemService.selectByPrimaryKey(1);
        if (withBLOBs != null) {
            return withBLOBs.getIntroduce();
        } else {
            return "请求无数据";
        }
    }
}
