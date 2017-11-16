<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set value="${pageContext.request.contextPath}" var="frontPath"/>
<html>
<head>
    <meta charset="utf-8">
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery/2.0.2/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="${frontPath}/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${frontPath}/css/index.css" rel="stylesheet" type="text/css">
    <title><c:if test="${not empty system}">${system.companyName}-</c:if>登录</title>
    <script type="text/javascript" src="${frontPath}/js/jquery.js"></script>
    <script type="text/javascript">
        var frontPath = '${frontPath}';
        var alertDiv;
        var msg;
        function login() {
            var username = $('#username').val();
            if (username == "") {
                msg.text("请输入用户名！");
                alertDiv.show();
                return false;
            }
            var password = $('#password').val();
            if (password == "") {
                msg.text("请输入密码！");
                alertDiv.show();
                return false;
            }
            $.post(frontPath + '/login.html', {
                username: username,
                password: password
            }, function (txt, success, response) {
                var result = response.responseText;
                if (result == 'success') {
                    window.location.href = frontPath + '/publish.html';
                } else {
                    msg.text("用户名或密码错误！");
                    alertDiv.show();
                }
            });
        }
        $(function () {
            alertDiv = $("#alertDiv");
            msg = $("#msg");

            document.onkeydown = function (e) {
                var ev = document.all ? window.event : e;
                if (ev.keyCode == 13) {
                    login();
                }
            };
            $("#loginBtn").click(function () {
                login();
            });
        });

    </script>
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
                        登录
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="container" style="margin-bottom: 100px;">
    <div class="section">
        <div class="row" id="alertDiv" style="display: none;">
            <div class="col-xs-6" style="margin-left: 25%;">
                <div class="alert alert-danger">
                    <i class="fa fa-minus-circle"></i><span id="msg" style="font-weight: bold;padding-left: 1%;"></span>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-xs-4" style="margin-left: 33%;">
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-sm-3" style="text-align: right;"><label for="username" class="control-label">用户名：<br></label></div>
                        <div class="col-sm-9">
                            <input type="email" class="form-control" id="username" placeholder="请输入用户名" name="username">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-3" style="text-align: right;"><label for="password" class="control-label">密码：</label></div>
                        <div class="col-sm-9">
                            <input type="password" class="form-control" id="password" placeholder="请输入密码"
                                   name="password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <div class="checkbox"><label><input type="checkbox" name="remember" value="1">保持登录</label></div>
                        </div>
                    </div>
                    <div class="form-group" style="margin-top: 40px;">
                        <div class="col-sm-offset-4 col-sm-8">
                            <button type="button" class="btn btn-default" id="loginBtn" style="width: 100px;">登&nbsp;&nbsp;录</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
    <jsp:include page="../include/bottom.jsp"/>
</body>
</html>