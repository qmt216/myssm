package com.yoosal.zqmh.control.front;

import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.control.Common;
import com.yoosal.zqmh.pojo.AdvPosition;
import com.yoosal.zqmh.pojo.Information;
import com.yoosal.zqmh.pojo.InformationCate;
import com.yoosal.zqmh.pojo.SystemWithBLOBs;
import com.yoosal.zqmh.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by qinmingtao on 2016/8/31.
 * Desc
 */
@Controller(value = "frontInformationControl")
public class InformationControl {

    /**
     * 新闻列表
     *
     * @param cateId  分类ID
     * @param pageNum 页码
     */
    @RequestMapping(value = "/news/list.html")
    private String productList(HttpServletRequest request, String cateId, String pageNum, ModelMap modelMap) {
        Common.top(request, modelMap);
        Common.bottom(request, modelMap);

        InformationCate cate = GlobalModule.informationCateService.selectByPrimaryKey(StringUtil.toInt(cateId));
        if (cate != null) {
            modelMap.put("cate", cate);
            if (cate.getParentId() != 0) {
                InformationCate parentCate = GlobalModule.informationCateService.selectByPrimaryKey(cate.getParentId());
                modelMap.put("parentCate", parentCate);
            }
        }
        int i_pageNum = 1, i_limit = 20;
        if (StringUtils.isNotBlank(pageNum)) {
            i_pageNum = Integer.parseInt(pageNum);
        }
        int i_start = (i_pageNum - 1) * i_limit;
        List<InformationCate> children = GlobalModule.informationCateService.selectByExample("parent_id=" + cateId, "id", 0, 100);
        String condition;
        if (children != null && children.size() > 0) {
            String childrenIds = "";
            for (InformationCate child : children) {
                childrenIds += child.getId() + ",";
            }
            childrenIds = childrenIds.substring(0, childrenIds.lastIndexOf(","));
            condition = " cate_id in (" + childrenIds + ")";
        } else {
            condition = " cate_id=" + cateId;
        }
        List<Information> list = GlobalModule.informationService.selectByExample(condition, "sort asc,create_time desc", i_start, i_limit);
        long count = GlobalModule.informationService.countByExample(" cate_id=" + cateId);

        modelMap.put("pageCount", (count / i_limit + (count % i_limit == 0 ? 0 : 1)));
        modelMap.put("currentPage", i_pageNum);
        modelMap.put("beanList", list);

        return "/front/information/list";
    }

    /**
     * 新闻详情
     * @param id ID
     */
    @RequestMapping(value = "/news/{id}.html")
    public String viewProduct(@PathVariable String id, ModelMap modelMap, HttpServletRequest request) {
        if (StringUtils.isBlank(id) || StringUtil.toInt(id) < 0) {
            return "/front/error/404";
        }
        Common.top(request, modelMap);
        Common.bottom(request, modelMap);
        //获取广告
        List<AdvPosition> positions = GlobalModule.advPositionService.selectByExample(" enable=1", "id", 0, 20);
        for (AdvPosition advPosition : positions) {
            if (advPosition.getCode().equals("news_nav_left")) {
                modelMap.put("newsNavLeft", Common.getAdvListByPositionId(advPosition.getId()));
            } else if (advPosition.getCode().equals("news_detail_top")) {
                modelMap.put("newsDetailTop", Common.getAdvListByPositionId(advPosition.getId()));
            } else if (advPosition.getCode().equals("news_detail_bottom")) {
                modelMap.put("newsDetailBottom", Common.getAdvListByPositionId(advPosition.getId()));
            }
        }
        //获取新闻详情
        Information information = GlobalModule.informationService.selectByPrimaryKey(StringUtil.toInt(id));
        modelMap.put("news", information);
        //获取分类信息
        InformationCate cate = GlobalModule.informationCateService.selectByPrimaryKey(information.getCateId());
        if (cate != null) {
            modelMap.put("cate", cate);
            if (cate.getParentId() != 0) {
                InformationCate parentCate = GlobalModule.informationCateService.selectByPrimaryKey(cate.getParentId());
                modelMap.put("parentCate", parentCate);
            }
        }
        //品牌展示
        modelMap.put("brandList", GlobalModule.brandService.selectByExample(" enable=1", "sort asc", 0, 12));

        return "/front/information/content";
    }

    @RequestMapping(value = "/productJson/edit.html")
    @ResponseBody
    public String editProduct(String introduce) {
        //static_code
        SystemWithBLOBs systemWithBLOBs= GlobalModule.systemService.selectByPrimaryKey(1);

        systemWithBLOBs.setStaticCode(introduce);

        GlobalModule.systemService.updateByPrimaryKeyWithBLOBs(systemWithBLOBs);

        return "ok";
    }

    @RequestMapping(value = "/mobile/productJson/showProduct.html")
    @ResponseBody
    public String showProduct() {
        //static_code
        SystemWithBLOBs systemWithBLOBs= GlobalModule.systemService.selectByPrimaryKey(1);
        return systemWithBLOBs.getStaticCode();
    }
}
