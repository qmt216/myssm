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
    <link href="${frontPath}/css/index_scroll.css" rel="stylesheet" type="text/css">
    <title><c:if test="${not empty system}">${system.companyName}-</c:if>首页</title>
    <style type="text/css">
        .name-style-0 {

        }

        .name-style-1 {
            color: red;
        }

        .name-style-2 {
            font-weight: bold;
        }

        .name-style-3 {
            color: red;
            font-weight: bold;
        }
        .panel-title a:hover{
            font-weight: bold;
        }
    </style>
</head>
<body>
<jsp:include page="include/header.jsp"/>
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

<!----------------------------------------------------------------->
<script type="text/javascript" src="${frontPath}/js/slides.js">
</script>
<script type="text/javascript">
    $(function () {
        /*focus*/
        var sliderElement = $('.slider-carousel');
        sliderElement.slidesjs({
            width: 940,
            height: 630,
            play: {
                auto: true,
                effect: 'fade'
            },
            navigation: {
                effect: "fade"
            },
            pagination: {
                effect: "fade"
            },
            effect: {
                slide: {
                    speed: 400
                }
            }
        });
        var slidernav = sliderElement.find('.slidesjs-navigation');
        sliderElement.hover(function () {
            slidernav.stop().show()
        }, function () {
            slidernav.stop().hide()
        });

        slidernav.hover(function () {
            $(this).show();
        }), function () {
            $(this).show();
        }
    });
</script>

<!----------------------------------------------------------------->
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 adv2">
                <div class="column2 index_focus">
                    <div class="lft_pic">
                        <div class="slider-carousel">
                            <c:forEach items="${bigPicNews}" var="adv">
                            <div class="item">
                                <div class="pannel-image">
                                    <a href="${adv.url}" target="_blank">
                                        <img style="opacity: 1;"  src="${adv.pic}">
                                    </a>
                                </div>
                                <div class="titBg">&nbsp;</div>
                                <div class="subtitle">
                                    <h6><a href="${adv.url}" target="_blank">${adv.name}</a></h6>
                                </div>
                            </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="panel panel-primary" style="margin-bottom: 2px;">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="${frontPath}/news/list.html?cateId=6">协会动态</a></h3>
                    </div>
                    <div class="panel-body info-12">
                        <ol>
                            <c:forEach items="${informationList_1}" var="info">
                                <li>
                                    <a href="${frontPath}/news/${info.id}.html" title="${info.name}"
                                       class="name-style-${info.nameStyle}">${fn:substring(info.name,0 , 22)}</a>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
                <div class="panel panel-primary" style="margin-top: 20px;">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="${frontPath}/news/list.html?cateId=45">通知公告</a></h3>
                    </div>
                    <div class="panel-body info-6" style="height: 150px;">
                        <ol>
                            <c:forEach items="${informationList_2}" var="info">
                                <li>
                                    <a href="${frontPath}/news/${info.id}.html" title="${info.name}"
                                       class="name-style-${info.nameStyle}">${fn:substring(info.name,0 , 22)}</a>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-xs-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="${frontPath}/news/list.html?cateId=8">职业培训</a></h3>
                    </div>
                    <div class="panel-body info-6">
                        <ol>
                            <c:forEach items="${informationList1}" var="info">
                                <li>
                                    <a href="${frontPath}/news/${info.id}.html" title="${info.name}"
                                       class="name-style-${info.nameStyle}">${fn:substring(info.name,0 , 22)}</a>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="${frontPath}/news/list.html?cateId=9">大型活动</a></h3>
                    </div>
                    <div class="panel-body info-6">
                        <ol>
                            <c:forEach items="${informationList2}" var="info">
                                <li>
                                    <a href="${frontPath}/news/${info.id}.html" title="${info.name}"
                                       class="name-style-${info.nameStyle}">${fn:substring(info.name,0 , 22)}</a>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="${frontPath}/news/list.html?cateId=20">企业新闻</a></h3>
                    </div>
                    <div class="panel-body info-6">
                        <ol>
                            <c:forEach items="${informationList3}" var="info">
                                <li>
                                    <a href="${frontPath}/news/${info.id}.html" title="${info.name}"
                                       class="name-style-${info.nameStyle}">${fn:substring(info.name,0 , 22)}</a>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="section">
    <div class="container">
        <div class="row adv1">
            <div class="col-xs-8">
                <c:if test="${fn:length(indexMidLeft)>0}">
                    <c:set var="adv" value="${indexMidLeft[0]}"/>
                    <a href="${adv.url}" target="_blank" title="${adv.name}">
                        <img src="${adv.pic}" class="img-responsive">
                    </a>
                </c:if>
            </div>
            <div class="col-xs-4">
                <c:if test="${fn:length(indexMidRight)>0}">
                    <c:set var="adv" value="${indexMidRight[0]}"/>
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
            <div class="col-xs-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="${frontPath}/news/list.html?cateId=10">产业集群</a></h3>
                    </div>
                    <div class="panel-body info-6">
                        <ol>
                            <c:forEach items="${informationList4}" var="info">
                                <li>
                                    <a href="${frontPath}/news/${info.id}.html" title="${info.name}"
                                       class="name-style-${info.nameStyle}">${fn:substring(info.name,0 , 22)}</a>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="${frontPath}/news/list.html?cateId=12">3586工程</a></h3>
                    </div>
                    <div class="panel-body info-6">
                        <ol>
                            <c:forEach items="${informationList5}" var="info">
                                <li>
                                    <a href="${frontPath}/news/${info.id}.html" title="${info.name}"
                                       class="name-style-${info.nameStyle}">${fn:substring(info.name,0 , 22)}</a>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
            <div class="col-xs-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><a href="${frontPath}/news/list.html?cateId=15">政策法规</a> </h3>
                    </div>
                    <div class="panel-body info-6">
                        <ol>
                            <c:forEach items="${informationList6}" var="info">
                                <li>
                                    <a href="${frontPath}/news/${info.id}.html" title="${info.name}"
                                       class="name-style-${info.nameStyle}">${fn:substring(info.name,0 , 22)}</a>
                                </li>
                            </c:forEach>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="include/brand.jsp"/>
<jsp:include page="include/bottom.jsp"/>
</body>
</html>