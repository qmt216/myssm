<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="http://www.chinaga.org" var="frontPath"/>
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
    <title>${system.companyName}-${video.name}</title>
    <meta name="keyword" content="在线视频,${system.companyName}">
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
                    <li>
                        <a href="${frontPath}/video/list.html">在线视频</a> <span class="divider"></span>
                    </li>
                    <c:if test="${not empty video}">
                        <li>
                                ${video.name} <span class="divider"></span>
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
            <div class="col-xs-12">
                <h3 style="text-align: center;line-height: 40px;">
                    ${video.name}
                </h3>
                <c:if test="${not empty video.url}">
                   <span id="video" style="width: 1000px; height: 500px; display: block; margin:0 auto"></span>
                </c:if>
                <p style="color: #A19696;text-align: center;margin: 20px 0 40px 0;">
                    <small>发布时间：<fmt:formatDate value="${video.editTime}" type="both" pattern="yyyy-MM-dd hh:mm:ss"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:if
                            test="${not empty video.view}">观看次数：${video.view}</c:if></small>
                </p>
                <p style="margin-top: 15px;line-height: 30px;">
                    ${video.intro}
                </p>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${frontPath}/ckplayer6.8/ckplayer/ckplayer.js" charset="utf-8"></script>
<script type="text/javascript">
    var flashvars = {
        f: '${frontPath}${video.url}',
        c: 0,
        b: 1,
        i: '${frontPath}${video.cover}'
    };
    var params = {bgcolor: '#FFF', allowFullScreen: true, allowScriptAccess: 'always', wmode: 'transparent'};
    CKobject.embedSWF('/ckplayer6.8/ckplayer/ckplayer.swf', 'video', 'ckplayer_a1', '1000', '500', flashvars, params);
    /*
     CKobject.embedSWF(播放器路径,容器id,播放器id/name,播放器宽,播放器高,flashvars的值,其它定义也可省略);
     下面三行是调用html5播放器用到的
     */
    var video = ['${frontPath}${video.url}->video/mp4'];
    var support = ['iPad', 'iPhone', 'ios', 'android+false', 'msie10+false'];
    CKobject.embedHTML5('video', 'ckplayer_a1', 1000, 500, video, flashvars, support);


    function closelights() {//关灯
        alert(' 本演示不支持开关灯');
    }
    function openlights() {//开灯
        alert(' 本演示不支持开关灯');
    }
</script>
<jsp:include page="../include/bottom.jsp"/>
</body>
</html>
