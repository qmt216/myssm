<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath}" var="frontPath"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
    <script type="text/javascript" src="http://cdn.bootcss.com/jquery/2.0.2/jquery.min.js"></script>
    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link href="${frontPath}/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${frontPath}/css/index.css" rel="stylesheet" type="text/css">
    <title>${system.companyName}-${news.name}</title>
    <meta name="keyword" content="新闻内容,${system.companyName}">
    <meta name="description" content="${system.companySummary}">
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="section">
    <div class="container">
        <div class="row adv1">
            <div class="col-xs-12">
                <c:if test="${fn:length(newsNavLeft)>0}">
                    <c:set var="adv" value="${newsNavLeft[0]}"/>
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
                    <c:if test="${not empty parentCate}">
                        <li>
                            <a href="${frontPath}/news/list.html?cateId=${parentCate.id}">${parentCate.name}</a> <span class="divider"></span>
                        </li>
                    </c:if>
                    <c:if test="${not empty cate}">
                        <li>
                            <a href="${frontPath}/news/list.html?cateId=${cate.id}">${cate.name}</a> <span class="divider"></span>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-xs-8">
                <h3 style="text-align: center;line-height: 40px;">
                    ${news.name}
                </h3>
                <p style="color: #A19696;text-align: center;margin: 20px 0 40px 0;"> <small><fmt:formatDate value="${news.createTime}" type="both" pattern="yyyy-MM-dd hh:mm:ss"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${not empty news.fr}">来源：${news.fr}</c:if></small> </p>
                <p style="margin-top: 15px;line-height: 30px;">
                    ${news.content}
                </p>
                <p style="margin:20px 0; float: right;">（编辑：${news.author}）</p>
            </div>
            <div class="col-xs-4">
                <c:if test="${fn:length(newsDetailTop)>0}">
                    <c:set var="adv" value="${newsDetailTop[0]}"/>
                    <a href="${adv.url}" target="_blank" title="${adv.name}">
                        <img src="${adv.pic}" class="img-responsive">
                    </a>
                </c:if>
                <c:if test="${fn:length(newsDetailBottom)>0}">
                    <c:set var="adv" value="${newsDetailBottom[0]}"/>
                    <a href="${adv.url}" target="_blank" title="${adv.name}">
                        <img src="${adv.pic}" class="img-responsive">
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../include/brand.jsp"/>
<jsp:include page="../include/bottom.jsp"/>
</body>
</html>
