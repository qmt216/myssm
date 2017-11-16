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
    <title>${system.companyName}-在线下载</title>
    <meta name="keyword" content="在线下载,${system.companyName}">
    <meta name="description" content="${system.companySummary}">
    <style type="text/css">
        .list-group-item{
            border: none;
        }
    </style>
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
                        在线下载
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
                <c:choose>
                    <c:when test="${empty beanList}">
                        <center style="margin: 20px 0;">抱歉，没有可以显示的内容</center>
                    </c:when>
                    <c:otherwise>
                        <ul class="list-group">
                            <c:forEach items="${beanList}" var="bean">
                                <li class="list-group-item">
                                    <a href="${frontPath}${bean.url}" target="_blank">${bean.fileName}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<c:if test="${not empty beanList}">
    <div class="section">
        <div class="container">
            <div class="row">
                <div class="col-xs-6">
                    <ul class="pagination">
                        <c:set var="noPrePage"
                               value="${(pageCount eq 0) || (pageCount eq 1) || (pageCount eq currentPage) || ((i_pageNum - 1)<1)}"/>
                        <c:set var="noNextPage"
                               value="${(pageCount eq 0) || (pageCount eq 1) || (pageCount eq currentPage) || ((i_pageNum + 1)>pageCount)}"/>
                        <c:choose>
                            <c:when test="${noPrePage}">
                                <c:set var="prePageUrl" value="#"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="prePageUrl"
                                       value="${frontPath}/download/list.html?pageNum=${(i_pageNum - 1)}"/>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${noNextPage}">
                                <c:set var="nextPageUrl" value="#"/>
                            </c:when>
                            <c:otherwise>
                                <c:set var="nextPageUrl"
                                       value="${frontPath}/download/list.html?pageNum=${(i_pageNum + 1)}"/>
                            </c:otherwise>
                        </c:choose>
                        <li
                                <c:if test="${noPrePage}">class="disabled"</c:if> >
                            <a href="${prePageUrl}">上一页</a>
                        </li>
                        <c:forEach begin="1" end="${pageCount}" var="page">
                            <c:set var="current" value="${page eq currentPage}"/>
                            <c:choose>
                                <c:when test="${current}">
                                    <c:set var="url" value="#"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set var="url" value="${frontPath}/download/list.html?pageNum=${page}"/>
                                </c:otherwise>
                            </c:choose>
                            <li <c:if test="${current}">class="disabled"</c:if>>
                                <a href="${url}">${page}</a>
                            </li>
                        </c:forEach>
                        <li <c:if test="${noNextPage}">class="disabled"</c:if>>
                            <a href="${nextPageUrl}">下一页</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</c:if>

<jsp:include page="../include/bottom.jsp"/>
</body>
</html>