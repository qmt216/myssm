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
    <title>${system.companyName}-品牌展示</title>
    <meta name="keyword" content="品牌展示,${system.companyName}">
    <meta name="description" content="${system.companySummary}">
</head>
<body>
<jsp:include page="../include/header.jsp"/>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <ul class="breadcrumb">
                    <li>
                        <a href="/">首页</a> <span class="divider"></span>
                    </li>
                    <li class="active">
                        品牌展示
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="section" style="min-height: 400px;">
    <div class="container">
        <c:choose>
            <c:when test="${fn:length(brandList)>0}">
                <c:forEach items="${brandList}" var="brand" varStatus="s">
                    <c:if test="${s.index%6==0}"><div class="row pingpai"></c:if>
                    <div class="col-xs-2">
                        <a href="${brand.url}" target="_blank" title="${brand.name}">
                            <img src="${brand.pic}" class="img-responsive">
                        </a>
                    </div>
                    <c:if test="${(s.index!=0 && (s.index+1)%6==0) || ((s.index+1)%6!=0 && s.last)}"></div></c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <center style="margin: 20px 0">抱歉，没有可以展示的内容</center>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<jsp:include page="../include/bottom.jsp"/>
</body>
</html>
