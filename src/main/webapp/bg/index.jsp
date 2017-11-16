<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.yoosal.zqmh.pojo.Admin" %>
<%@ page import="com.yoosal.zqmh.GlobalVariable" %>
<%@ page import="com.yoosal.zqmh.pojo.SystemWithBLOBs" %>
<%@ page import="com.yoosal.zqmh.GlobalModule" %>
<%@ page import="java.util.List" %>
<%
    Admin admin = (Admin) request.getSession().getAttribute(GlobalVariable.ADMIN_SESSION_KEY);
    if (admin == null) {
        response.sendRedirect("/bg/login.jsp");
    }
    List<SystemWithBLOBs> systems = GlobalModule.systemService.queryAll("sort", 0, 1);
    if(systems!=null && systems.size()>0){
        request.setAttribute("bean",systems.get(0));
    }
%>
<%--
  Created by IntelliJ IDEA.
  User: ibm
  Date: 13-12-26
  Time: 下午11:09
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="frontPath"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=7"/>
    <title>${bean.companyName}-网站管理后台</title>

    <link href="${frontPath}/bg/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="${frontPath}/bg/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
    <link href="${frontPath}/bg/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
    <link href="${frontPath}/bg/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/\-" media="screen"/>
    <!--[if IE]>
    <link href="/bg/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
    <![endif]-->
    <script src="${frontPath}/bg/dwz/js/speedup.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/jquery.cookie.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/jquery.validate.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>

    <script type="text/javascript" src="${frontPath}/js/xheditor/xheditor.js"></script>
    <script type="text/javascript" src="${frontPath}/js/xheditor/xheditor_lang/zh-cn.js"></script>

    <script src="${frontPath}/bg/dwz/uploadify/scripts/swfobject.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>
    <!--dwz库-->
    <script src="${frontPath}/bg/dwz/js/dwz.core.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.util.date.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.drag.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.tree.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.accordion.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.ui.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.theme.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.navTab.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.tab.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.resize.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.dialog.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.stable.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.ajax.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.pagination.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.database.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.effects.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.panel.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.history.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.combox.js" type="text/javascript"></script>
    <script src="${frontPath}/bg/dwz/js/dwz.print.js" type="text/javascript"></script>
    <!--dwz库 end-->
    <script src="${frontPath}/bg/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
    <!--[if IE]>
    <link href="${frontPath}/bg/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
    <![endif]-->
    <style type="text/css">
        #header{height:85px}
        #leftside, #container, #splitBar, #splitBarProxy{top:90px}
    </style>

    <script type="text/javascript">
        var APP = '__APP__';
        function fleshVerify() {
            //重载验证码
            $('#verifyImg').attr("src", '__APP__/Public/verify/' + new Date().getTime());
        }
        function dialogAjaxMenu(json) {
            dialogAjaxDone(json);
            if (json.statusCode == DWZ.statusCode.ok) {
                $("#sidebar").loadUrl("__APP__/Public/menu");
            }
        }
        function navTabAjaxMenu(json) {
            navTabAjaxDone(json);
            if (json.statusCode == DWZ.statusCode.ok) {
                $("#sidebar").loadUrl("__APP__/Public/menu");
            }
        }
        $(function () {
            DWZ.init("${frontPath}/bg/dwz/dwz.frag.xml", {
                loginUrl: "login.html",
                debug: false,
                statusCode: {ok: 1, error: 0},
                callback: function () {
                    initEnv();
                    $("#themeList").theme({themeBase: "${frontPath}/bg/dwz/themes"});
//            $("#sidebar").loadUrl("__APP__/Public/menu");
                }
            });
        });
        //清理浏览器内存,只对IE起效，FF不需要
        if ($.browser.msie) {
            window.setInterval("CollectGarbage();", 10000);
        }
    </script>
</head>

<body scroll="no">
<div id="layout">
    <div id="header">
        <div class="headerNav">
            <a class="logo" href="#">中国礼仪休闲用品工业协会管理后台</a>
            <ul class="nav">
                <li><a href="${frontPath}/" target="_blank">前台首页</a></li>
                <li><a href="${frontPath}/bg/admin/changePwd.html" target="dialog" width="520" height="190">修改密码</a>
                </li>
                <li><a href="${frontPath}/bg/admin/logout.html">退出</a></li>
            </ul>
            <ul class="themeList" id="themeList">
                <li theme="default">
                    <div class="selected">蓝色</div>
                </li>
                <li theme="green">
                    <div>绿色</div>
                <li theme="purple">
                    <div>紫色</div>
                </li>
                <li theme="silver">
                    <div>银色</div>
                </li>
                <li theme="azure">
                    <div>天蓝</div>
                </li>
            </ul>
        </div>
        <!-- navMenu -->
        <div id="navMenu" style="padding-left:210px;">
            <ul>
                <li class="selected"><a href="${frontPath}/bg/menu.html?g=0"><span>新闻管理</span></a></li>
                <li><a href="${frontPath}/bg/menu.html?g=1"><span>栏目管理</span></a></li>
                <li><a href="${frontPath}/bg/menu.html?g=2"><span>下载管理</span></a></li>
                <li><a href="${frontPath}/bg/menu.html?g=3"><span>广告管理</span></a></li>
                <li><a href="${frontPath}/bg/menu.html?g=4"><span>页脚管理</span></a></li>
                <li><a href="${frontPath}/bg/menu.html?g=5"><span>系统设置</span></a></li>
                <li><a href="${frontPath}/bg/menu.html?g=6"><span>用户管理</span></a></li>
            </ul>
        </div>
    </div>

    <div id="leftside">
        <div id="sidebar_s">
            <div class="collapse">
                <div class="toggleCollapse">
                    <div></div>
                </div>
            </div>
        </div>
        <div id="sidebar">
            <div class="toggleCollapse"><h2>主菜单</h2>

                <div>收缩</div>
            </div>
            <c:import url="/bg/menu.html?g=0" charEncoding="utf-8"/>
        </div>
    </div>
    <div id="container">
        <div id="navTab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
                    <ul class="navTab-tab">
                        <li tabid="main" class="main"><a href="javascript:;"><span><span
                                class="home_icon">我的主页</span></span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft">left</div>
                <!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
                <div class="tabsRight">right</div>
                <!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
                <div class="tabsMore">more</div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">我的主页</a></li>
            </ul>
            <div class="navTab-panel tabsPageContent layoutBox">
                <div class="page unitBox">
                    <div class="accountInfo">
                        <div class="right">
                            <p><%=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date())%>
                            </p>
                        </div>
                        <p><span>官方网站后台管理系统</span></p>

                        <p>欢迎光临, 超级管理员</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<div id="footer">Copyright &copy; 2016 &nbsp;&nbsp;&nbsp;&nbsp;<a href="http://keji.zqo2o.com" target="_blank">中轻门户系统</a>
</div>
</body>
</html>