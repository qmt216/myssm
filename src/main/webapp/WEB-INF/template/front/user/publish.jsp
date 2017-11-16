<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath}" var="frontPath"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=7"/>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery/2.0.2/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="${frontPath}/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${frontPath}/css/index.css" rel="stylesheet" type="text/css">
    <title><c:if test="${not empty system}">${system.companyName}-</c:if>我的主页</title>
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="section">
    <div class="container">
        <div class="row adv1">
            <div class="col-xs-8">
                <c:if test="${fn:length(indexNavLeft)>0}">
                    <c:set var="adv" value="${indexNavLeft[0]}"/>
                    <a href="${adv.url}" target="_blank" title="${adv.name}">
                        <img src="${adv.pic}" class="img-responsive">
                    </a>
                </c:if>
            </div>
            <div class="col-xs-4">
                <c:if test="${fn:length(indexNavRight)>0}">
                    <c:set var="adv" value="${indexNavRight[0]}"/>
                    <a href="${adv.url}" target="_blank" title="${adv.name}">
                        <img src="${adv.pic}" class="img-responsive">
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <ul class="breadcrumb">
                    <li>
                        <a href="/">首页</a> <span class="divider"></span>
                    </li>
                    <li class="active">
                        我的主页
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <blockquote>
                    <p>
                        欢迎您，${user.name} &nbsp;&nbsp;&nbsp;&nbsp;<a href="/logout.html">退出登陆</a>
                    </p>
                    <small>您剩余的发布条数：<cite>${user.adNum}</cite></small>
                </blockquote>
            </div>
            <div class="col-xs-12">

                <ul class="nav nav-tabs" style="background-color: white;">
                    <li role="presentation" class="active"><a href="#publishPane" data-toggle="tab"
                                                              id="publishTab">发布新闻</a>
                    </li>
                    <li role="presentation"><a href="#publishListPane" data-toggle="tab" id="historyTab">发布历史</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="publishPane">
                        <form class="form-horizontal" role="form" style="margin: 20px 0 40px 0;">
                            <div class="row" id="alertDiv" style="display: none;">
                                <div class="col-xs-6" style="margin-left: 25%;">
                                    <div class="alert alert-danger">
                                        <i class="fa fa-minus-circle"></i>
                                        <span id="msg" style="font-weight: bold;padding-left: 1%;"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-2"><label for="content" class="control-label">标题：<br></label></div>
                                <div class="col-sm-10">
                                    <input type="hidden" name="id" id="id"/>
                                    <input type="text" name="name" style="width: 400px;" id="name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-2"><label for="content" class="control-label">内容：<br></label></div>
                                <div class="col-sm-10">
                                    <textarea name="content" rows="15" cols="130" id="content"> </textarea>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <button type="button" class="btn btn-default" id="publishBtn" style="width: 100px;">
                                        发&nbsp;&nbsp;布
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane" id="publishListPane">
                        <c:choose>
                            <c:when test="${fn:length(newsList)>0}">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th style="width: 10%;">编号</th>
                                        <th style="width: 50%;">新闻名称</th>
                                        <th style="width: 20%;">发布时间</th>
                                        <th style="width: 20%;">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${newsList}" var="news" varStatus="s">
                                        <tr>
                                            <td>${s.index+1}</td>
                                            <td>
                                                <a href="${frontPath}/news/${news.id}.html"
                                                   title="${news.name}">${news.name}</a>
                                            </td>
                                            <td><fmt:formatDate value="${news.createTime}" type="both"
                                                                pattern="yyyy-MM-dd hh:mm:ss"/></td>
                                            <td>
                                                <button type="button" class="btn btn-default"
                                                        onclick="edit('${news.id}')">修改
                                                </button>
                                                <button type="button" class="btn btn-default"
                                                        onclick="del('${news.id}')">删除
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise>
                                <center style="margin: 20px auto;">您还没有发布新闻</center>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="${frontPath}/js/xheditor/xheditor.js"></script>
<script type="text/javascript" src="${frontPath}/js/xheditor/xheditor_lang/zh-cn.js"></script>
<script type="text/javascript">
    var frontPath = "${frontPath}";
    var editor = $('#content').xheditor({
        upImgUrl: frontPath + "/bg/information/uploadImg.html",
        upImgExt: "jpg,jpeg,gif,png",
        tools: 'full',
        loadCSS: '<style>pre{margin-left:2em;border-left:3px solid #CCC;padding:0 1em;}</style>',
        onUpload: insertUpload
    });

    function insertUpload(msg) {
    }
    var alertDiv;
    var msg;
    $(function () {
        alertDiv = $("#alertDiv");
        msg = $("#msg");
        $("#publishBtn").click(function () {
            var name = $('#name').val();
            if (name == "") {
                msg.text("请输入标题！");
                alertDiv.show();
                return false;
            }
            var content = editor.getSource();
            if (content == "") {
                msg.text("请输入内容！");
                alertDiv.show();
                return false;
            }
            $.post(frontPath + '/publish.html', {
                name: name,
                content: content,
                id: $("#id").val()
            }, function (txt, success, response) {
                var result = $.trim(response.responseText);
                if (result == "0") {
                    window.location.href = frontPath + '/publish.html?publishListPane=1';
                } else if (result == "1") {
                    msg.text("请重新登陆后发布！");
                    alertDiv.show();
                } else if (result == "1") {
                    msg.text("请填写完整后发布！");
                    alertDiv.show();
                } else if (result == "3") {
                    msg.text("抱歉，您已被冻结或没有发布条数！");
                    alertDiv.show();
                } else if (result == "3") {
                    msg.text("抱歉，系统错误，请稍后重试！");
                    alertDiv.show();
                }
            });
        });
    });

    var param = "${param.publishListPane}";
    if (param == "1") {
        $("#historyTab").trigger("click");
    }
    function edit(id) {
        $.post(frontPath + '/getInformation.html', {id: id}, function (data) {
            $("#id").val(data.id);
            $("#name").val(data.name);
            editor.setSource(data.content);
            $("#publishTab").trigger("click");
        });
    }
    function del(id) {
        if (confirm("您确认要删除嘛？")) {
            $.post(frontPath + '/delInformation.html', {id: id}, function (data) {
                var result = $.trim(data);
                if (result == "0") {
                    window.location.href = frontPath + '/publish.html?publishListPane=1';
                } else if (result == "1") {
                    alert("抱歉，系统错误，请稍后重试！");
                }
            });
        }
    }
</script>
<jsp:include page="../include/bottom.jsp"/>
</body>
</html>