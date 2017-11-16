package com.yoosal.zqmh.control.front;

import com.alibaba.fastjson.JSONObject;
import com.yoosal.zqmh.GlobalModule;
import com.yoosal.zqmh.GlobalVariable;
import com.yoosal.zqmh.control.Common;
import com.yoosal.zqmh.pojo.*;
import com.yoosal.zqmh.util.CookieUtil;
import com.yoosal.zqmh.util.MD5;
import com.yoosal.zqmh.util.StringUtil;
import com.yoosal.zqmh.util.search.ElasticSearchHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: qinmingtao
 * Date: 13-12-26
 * Time: 上午12:31
 * To change this template use File | Settings | File Templates.
 */
@Controller("frontIndexControl")
public class IndexControl {
    private static Logger logger = Logger.getLogger(IndexControl.class);
    /**
     * 首页
     */
    @RequestMapping("/index.html")
    private String index(HttpServletRequest request, ModelMap modelMap) {
        Common.top(request, modelMap);
        Common.bottom(request, modelMap);

        //获取广告
        List<AdvPosition> positions = GlobalModule.advPositionService.selectByExample(" enable=1", "id", 0, 20);
        for (AdvPosition advPosition : positions) {
            if (advPosition.getCode().equals("index_nav_left")) {
                modelMap.put("indexNavLeft", Common.getAdvListByPositionId(advPosition.getId()));
            } else if (advPosition.getCode().equals("index_nav_right")) {
                modelMap.put("indexNavRight", Common.getAdvListByPositionId(advPosition.getId()));
            } else if (advPosition.getCode().equals("index_mid_left")) {
                modelMap.put("indexMidLeft", Common.getAdvListByPositionId(advPosition.getId()));
            } else if (advPosition.getCode().equals("index_mid_right")) {
                modelMap.put("indexMidRight", Common.getAdvListByPositionId(advPosition.getId()));
            } else if (advPosition.getCode().equals("big_pic_news")) {
                modelMap.put("bigPicNews", Common.getAdvListByPositionId(advPosition.getId()));
            }
        }
        //获取广告
        modelMap.put("informationList_1", Common.getInformationByCateId(6, 9)); //协会动态
        modelMap.put("informationList_2", Common.getInformationByCateId(45, 5)); //通知公告

        modelMap.put("informationList1", Common.getInformationByCateId(9, 6)); //职业培训
        modelMap.put("informationList2", Common.getInformationByCateId(19, 6)); //大型活动
        modelMap.put("informationList3", Common.getInformationByCateId(20, 6)); //企业新闻
        modelMap.put("informationList4", Common.getInformationByCateId(10, 6)); //产业集群
        modelMap.put("informationList5", Common.getInformationByCateId(12, 6)); //3586工程
        modelMap.put("informationList6", Common.getInformationByCateId(15, 6)); //政策法规

        //品牌展示
        modelMap.put("brandList", GlobalModule.brandService.selectByExample(" enable=1", "sort asc", 0, 12));
        return "/front/index";
    }

    /**
     * 下载列表
     *
     * @param pageNum 页码
     */
    @RequestMapping("/download/list.html")
    private String resolveList(HttpServletRequest request, String pageNum, ModelMap modelMap) {
        Common.top(request, modelMap);
        Common.bottom(request, modelMap);

        int i_pageNum = 1, i_limit = 20;
        if (StringUtils.isNotBlank(pageNum)) {
            i_pageNum = Integer.parseInt(pageNum);
        }
        int i_start = (i_pageNum - 1) * i_limit;
        List<Download> list = GlobalModule.downloadService.selectByExample(" enable=1 ", "id desc", i_start, i_limit);
        long count = GlobalModule.downloadService.countByExample(" enable=1 ");

        modelMap.put("pageCount", (count / i_limit + (count % i_limit == 0 ? 0 : 1)));
        modelMap.put("currentPage", i_pageNum);
        modelMap.put("beanList", list);

        return "/front/download/list";
    }

    /**
     * 下载详情
     *
     * @param id ID
     */
    @RequestMapping(value = "/download/{id}.html")
    public String viewResolve(@PathVariable String id, ModelMap modelMap, HttpServletRequest request) {
        if (StringUtils.isBlank(id)) {
            return "/front/error/404";
        }
        Common.top(request, modelMap);
//        modelMap.put("bean", ZeroModules.resolve.getById(ResolveBean.class, id));
        return "/front/download/content";
    }

    /**
     * 关于我们
     */
    @RequestMapping("/about.html")
    private String about(HttpServletRequest request, ModelMap modelMap) {
        Common.top(request, modelMap);
        Common.bottom(request, modelMap);
        return "/front/about";
    }

    @RequestMapping(value = "/message/insert.html")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public String insert(HttpServletRequest request, String linkMan, String linkNum, String email, String address) throws Exception {
        return null;
    }

    /**
     * 登陆页
     */
    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, ModelMap modelMap) {
        Common.top(request, modelMap);
        Common.bottom(request, modelMap);
        return "/front/user/login";
    }

    /**
     * 登陆页
     */
    @RequestMapping(value = "/logout.html")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(GlobalVariable.USER_SESSION_KEY);
        CookieUtil.removeCookie(GlobalVariable.USER_COOKIE_KEY, request, response);
        return "redirect:/index.html";
    }

    /**
     * 登陆页逻辑处理
     */
    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    @ResponseBody
    public String loginAjax(HttpServletRequest request, HttpServletResponse response, String username, String password) {
        List<User> users = GlobalModule.userService.selectByExample("name='" + username + "'", "id desc", 0, 1);
        if (users != null && users.size() > 0 && users.get(0).getPassword().equals(MD5.getMD5ofString(password))) {
            CookieUtil.addCookie(GlobalVariable.USER_COOKIE_KEY, users.get(0).getId() + "", "", -1, "/", response);
            request.getSession().setAttribute(GlobalVariable.USER_SESSION_KEY, users.get(0));
            return "success";
        }
        return "error";
    }

    /**
     * 发布页面
     */
    @RequestMapping(value = "/publish.html", method = RequestMethod.GET)
    public String publishPage(HttpServletRequest request, ModelMap modelMap) {
        Common.top(request, modelMap);
        Common.bottom(request, modelMap);
        User user = (User) request.getSession().getAttribute(GlobalVariable.USER_SESSION_KEY);
        if (user == null) {
            return "/front/user/login";
        } else {
            modelMap.put("user", user);
            List<Information> newsList = GlobalModule.informationService.selectByExample("user_id=" + user.getId(), "create_time desc", 0, 100);
            modelMap.put("newsList", newsList);
            return "/front/user/publish";
        }
    }

    /**
     * 发布逻辑处理
     */
    @RequestMapping(value = "/publish.html", method = RequestMethod.POST)
    @ResponseBody
    public String publishAjax(HttpServletRequest request, String name, String content, String id) {
        User user = (User) request.getSession().getAttribute(GlobalVariable.USER_SESSION_KEY);
        if (user == null) {
            return "1";
        }
        if (StringUtil.isEmpty(name) || StringUtil.isEmpty(content)) {
            return "2";
        }
        if (user.getFreeze() == 1 && user.getAdNum() <= 0) {
            return "3";
        }
        if (StringUtil.isNotEmpty(id) && StringUtil.toInt(id) > 0) { //修改
            Information information = GlobalModule.informationService.selectByPrimaryKey(StringUtil.toInt(id));
            if (information != null) {
                information.setName(name);
                information.setContent(content);
                GlobalModule.informationService.updateByPrimaryKey(information);
                return "0";
            }
        } else {  //发布
            Information information = new Information();
            information.setName(name);
            information.setContent(content);
            information.setNameStyle(0);
            information.setAuthor(user.getName());
            information.setUserId(user.getId());
            information.setFr(user.getRemark());
            information.setCateId(20);
            information.setSort(99);
            information.setCreateTime(new Date());
            if (GlobalModule.informationService.insert(information) > 0) {
                user.setAdNum(user.getAdNum() - 1);
                GlobalModule.userService.updateByPrimaryKey(user);
                return "0";
            }
        }
        return "4";
    }

    /**
     * 获取新闻
     */
    @RequestMapping(value = "/getInformation.html", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getInformation(String id) {
        Information information = GlobalModule.informationService.selectByPrimaryKey(StringUtil.toInt(id));
        return (JSONObject) JSONObject.toJSON(information);
    }

    /**
     * 删除新闻
     */
    @RequestMapping(value = "/delInformation.html", method = RequestMethod.POST)
    @ResponseBody
    public String delInformation(String id) {
        if (GlobalModule.informationService.deleteByPrimaryKey(StringUtil.toInt(id)) > 0) {
            return "0";
        } else {
            return "1";
        }
    }

    /**
     * 联系我们
     */
    @RequestMapping("/contact.html")
    public String viewContact(HttpServletRequest request, ModelMap modelMap) {
        Common.top(request, modelMap);
        return "/front/contact";
    }

    /**
     * 品牌展示
     */
    @RequestMapping("/brand/list.html")
    public String brandList(HttpServletRequest request, ModelMap modelMap) {
        Common.top(request, modelMap);
        Common.bottom(request, modelMap);
        //品牌展示
        modelMap.put("brandList", GlobalModule.brandService.selectByExample(" enable=1", "sort asc, id asc", 0, 1000));
        return "/front/brand/list";
    }

    /**
     * 品牌展示
     */
    @RequestMapping("/friend/list.html")
    public String friendList(HttpServletRequest request, ModelMap modelMap) {
        Common.top(request, modelMap);
        Common.bottom(request, modelMap);
        //友情链接
        modelMap.put("friendList", GlobalModule.friendService.selectByExample(" enable=1", "sort asc, id asc", 0, 1000));
        return "/front/friend/list";
    }

    /**
     * 品牌展示
     */
    @RequestMapping("/search.html")
    public String search(HttpServletRequest request, String keyword, String pageNum, ModelMap modelMap) throws UnsupportedEncodingException {
        Common.top(request, modelMap);
        Common.bottom(request, modelMap);
        if (StringUtil.isNotEmpty(keyword)) {
            if(logger.isDebugEnabled()){
                logger.debug("新闻搜索-keyword1:"+keyword);
            }
//            keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
            if(logger.isDebugEnabled()){
                logger.debug("新闻搜索-keyword2:"+new String(keyword.getBytes("ISO-8859-1"), "UTF-8"));
            }
//            keyword = new String(keyword.getBytes("ISO-8859-1"), "UTF-8");
            //分页
            int i_pageNum = 1, i_limit = 20;
            if (StringUtils.isNotBlank(pageNum)) {
                i_pageNum = Integer.parseInt(pageNum);
            }
            int i_start = (i_pageNum - 1) * i_limit;
            //
            try {
                SearchResponse response = ElasticSearchHandler.getInstance(true).prepareSearch(GlobalVariable.INDEX_NAME)
                        .setTypes(Information.class.getSimpleName())
                        .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                        .setQuery(QueryBuilders.queryStringQuery(keyword))
                        .setFrom(i_start).setSize(i_limit).setExplain(true)
                        .execute()
                        .actionGet();
                SearchHits searchHits = response.getHits();
                if (searchHits != null && searchHits.getHits() != null && searchHits.getHits().length > 0) {
                    List<Information> informationList = new ArrayList<Information>();
                    for (SearchHit searchHit : searchHits) {
                        Information i = new Information();
                        i.setId((Integer) searchHit.getSource().get("id"));
                        i.setName((String) searchHit.getSource().get("name"));
                        informationList.add(i);
                    }
                    long count = searchHits.getTotalHits();
                    modelMap.put("pageCount", (count / i_limit + (count % i_limit == 0 ? 0 : 1)));
                    modelMap.put("currentPage", i_pageNum);
                    modelMap.put("beanList", informationList);
                    modelMap.put("keyword", keyword);
                    modelMap.put("count", count);
                }
                if(logger.isDebugEnabled()){
                    logger.debug("返回结果-modelMap"+JSONObject.toJSONString(modelMap));
                }
            }catch (Exception e){
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        return "/front/information/search";
    }
}
