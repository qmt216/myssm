package com.yoosal.zqmh.control;

import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.pojo.*;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibm on 14-1-19.
 */
public class Common {
    public static void top(HttpServletRequest request, ModelMap modelMap) {
        List<SystemWithBLOBs> systemWithBLOBsList = GlobalModule.systemService.queryAll("id", 0, 1);
        if (systemWithBLOBsList != null && systemWithBLOBsList.size() > 0) {
            modelMap.put("system", systemWithBLOBsList.get(0));
        }
        modelMap.put("headCates", Common.getNav());
    }

    public static List<InformationCate> getNav() {
        List<InformationCate> informationCates = GlobalModule.informationCateService.queryAll("order_", 0, 1000);
        List<InformationCate> headCates = new ArrayList<InformationCate>();
        for (InformationCate cate : informationCates) {
            if (cate.getParentId() == 0) {
                headCates.add(cate);
            }
        }
        informationCates.removeAll(headCates);
        for (InformationCate cate : headCates) {
            if (cate.getChildren() == null) {
                cate.setChildren(new ArrayList<InformationCate>());
            }
            for (InformationCate child : informationCates) {
                if (child.getParentId().equals(cate.getId())) {
                    cate.getChildren().add(child);
                }
            }
        }
        return headCates;
    }

    public static void bottom(HttpServletRequest request, ModelMap modelMap) {
        List<Link> links = GlobalModule.linkService.selectByExample(" enable=1", " sort asc", 0, 10);
        modelMap.put("links", links);
    }

    /**
     * 通过广告位ID获取广告列表
     *
     * @param positionId 广告位ID
     * @return 广告列表
     */
    public static List<Adv> getAdvListByPositionId(int positionId) {
        return GlobalModule.advService.selectByExample("enable=1 and position_id=" + positionId, "sort asc", 0, 10);
    }

    /**
     * 通过分类ID，获取新闻列表
     *
     * @param cateId 分类ID
     * @param limit  获取条数
     * @return 新闻列表
     */
    public static List<Information> getInformationByCateId(int cateId, int limit) {
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
        return GlobalModule.informationService.selectByExample(condition, "sort asc,create_time desc", 0, limit);
    }
}
