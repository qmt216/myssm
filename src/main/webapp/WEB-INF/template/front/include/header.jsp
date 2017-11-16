<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="frontPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    function search() {
        var keyword = document.getElementById("keyword").value;
        if (keyword == "") {
            alert("请输入关键字");
        }else{
            location.href="/search.html?keyword="+keyword;
        }
    }
</script>
<style type="text/css">
    .adv1 img {
        max-height: 100px;
        width: 100%;
    }

    .adv2 img {
        max-height: 500px;
        width: 100%;
    }

    .section {
        padding: 5px 0;
    }

    ul.nav {
        width: 100%;
        background-color: #337cbb;
    }

    .navbar-default {
        border-color: #1D5789;
    }

    ul.nav > li {
        width: 11%;
        text-align: center;
    }

    .navbar-default .navbar-nav > li > a {
        color: #fff;
        font-size: 16px;
    }

    .navbar-default .navbar-nav > li > a:hover {
        color: #fff;
    }

    .panel-body {
        padding: 10px 15px;
    }

    .pingpai img {
        max-height: 100px;
    }

    .pingpai {
        padding: 5px;
        text-align: center;
    }

    .carousel-control.left {
        background-image: none;
    }

    .carousel-control.right {
        background-image: none;
    }

    .info-12 {
        height: 250px;
    }

    .info-6 {
        height: 170px;
    }

    .info-12 ol, .info-6 ol {
        list-style: none;
        padding: 0;
        line-height: 25px;
    }

    /* Template-specific stuff
 *
 * Customizations just for the template—these are not necessary for anything
 * with disabling the responsiveness.
 */

    /* Account for fixed navbar */

    /* Finesse the page header spacing */
    .page-header {

        margin-bottom: 30px;

    }
    .page-header .lead {

        margin-bottom: 10px;

    }


    /* Non-responsive overrides
     *
     * Utilitze the following CSS to disable the responsive-ness of the container,
     * grid system, and navbar.
     */

    /* Reset the container */
    .container {

        max-width: none !important;
        width: 1170px;

    }

    .container .navbar-header,
    .container .navbar-collapse {

        margin-right: 0;
        margin-left: 0;

    }

    /* Always float the navbar header */
    .navbar-header {

        float: left;

    }

    /* Undo the collapsing navbar */
    .navbar-collapse {

        display: block !important;
        height: auto !important;
        padding-bottom: 0;
        overflow: visible !important;

    }

    .navbar-toggle {

        display: none;

    }

    .navbar-brand {

        margin-left: -15px;

    }

    /* Always apply the floated nav */
    .navbar-nav {

        float: left;
        margin: 0;

    }
    .navbar-nav > li {

        float: left;

    }
    .navbar-nav > li > a {

        padding: 15px;

    }

    /* Redeclare since we override the float above */
    .navbar-nav.navbar-right {

        float: right;

    }

    /* Undo custom dropdowns */
    .navbar .open .dropdown-menu {

        position: absolute;
        float: left;
        background-color: #fff;
        border: 1px solid #cccccc;
        border: 1px solid rgba(0, 0, 0, 0.15);
        border-width: 0 1px 1px;
        border-radius: 0 0 4px 4px;
        -webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);
        box-shadow: 0 6px 12px rgba(0, 0, 0, 0.175);

    }
    .navbar .open .dropdown-menu > li > a {

        color: #333;

    }
    .navbar .open .dropdown-menu > li > a:hover,
    .navbar .open .dropdown-menu > li > a:focus,
    .navbar .open .dropdown-menu > .active > a,
    .navbar .open .dropdown-menu > .active > a:hover,
    .navbar .open .dropdown-menu > .active > a:focus {

        color: #fff !important;
        background-color: #428bca !important;

    }
    .navbar .open .dropdown-menu > .disabled > a,
    .navbar .open .dropdown-menu > .disabled > a:hover,
    .navbar .open .dropdown-menu > .disabled > a:focus {

        color: #999 !important;
        background-color: transparent !important;

    }
</style>
<div class="section" style="padding: 0;">
    <div class="container">
        <div class="row" style="height: 99px;">
            <div class="col-xs-6">
                <div>
                    <a href="/index.html">
                        <c:choose>
                            <c:when test="${not empty system.logo}">
                                <img src="${system.logo}"/>
                            </c:when>
                            <c:otherwise>
                                <img src="${frontPath}/images/logo.gif"/>
                            </c:otherwise>
                        </c:choose>
                    </a>
                </div>
            </div>
            <div class="col-xs-4" style="line-height: 99px;height: 99px;">
                <div class="input-group" style="top: 32%;">
                    <input type="text" placeholder="请输入搜索内容" class="form-control" id="keyword"/>
                    <span class="input-group-addon" style="cursor: pointer;" onclick="search()">
                        <i class="fa fa-search"></i>&nbsp;&nbsp;搜&nbsp;索</span>
                </div>
            </div>
            <div class="col-xs-2" style="text-align: right; padding-top: 14px;">
                <div>
                    <img src="${frontPath}/images/erweima.jpg" style="height: 72px; width: 72px;">
                </div>
            </div>
        </div>
    </div>
</div>

<div class="section">
    <div class="container" style="width: 100%; padding-left: 0; padding-right: 0; background-color: #337CBB;">
        <div class="navbar navbar-default" style="width: 1170px; margin: 0 auto; border: none;">
            <ul class="nav navbar-nav">
                <c:forEach items="${headCates}" begin="0" end="8" var="cate">
                    <c:set var="hasChildren" value="${not empty cate.children}"/>
                    <li <c:if test="${hasChildren}">class="dropdown"</c:if>>
                        <a
                                <c:if test="${not empty cate.url}">href="${cate.url}"</c:if>
                                <c:if test="${hasChildren}">href="#" data-toggle="dropdown"
                                class="dropdown-toggle"</c:if>
                                <c:if test="${!hasChildren}">href="${frontPath}/news/list.html?cateId=${cate.id}"</c:if>
                                >${cate.name}
                            <c:if test="${hasChildren}"><span class="caret"></span></c:if>
                        </a>
                        <c:if test="${hasChildren}">
                            <ul class="dropdown-menu">
                                <c:forEach items="${cate.children}" var="child">
                                    <li><a
                                            <c:if test="${not empty child.url}">href="${child.url}"</c:if>
                                            <c:if test="${empty child.url}">href="${frontPath}/news/list.html?cateId=${child.id}"</c:if>
                                            >${child.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
        </div>
        </div>
        <div class="container" style="width: 100%; padding-left: 0; padding-right: 0; background-color: #e7e7e7;">
        <div class="navbar navbar-default" style="border-top: none;width: 1170px; margin: 0 auto; border: none;">

            <ul class="nav navbar-nav" style="background-color:#e7e7e7;">
                <c:forEach items="${headCates}" begin="9" end="18" var="cate">
                    <c:set var="hasChildren" value="${not empty cate.children}"/>
                    <li <c:if test="${hasChildren}">class="dropdown"</c:if>>
                        <a style="color: #000000;"
                                <c:if test="${not empty cate.url}">href="${cate.url}"</c:if>
                                <c:if test="${hasChildren}">href="#" data-toggle="dropdown"
                                class="dropdown-toggle"</c:if>
                                <c:if test="${!hasChildren}">href="${frontPath}/news/list.html?cateId=${cate.id}"</c:if>
                                >${cate.name}
                            <c:if test="${hasChildren}"><span class="caret"></span></c:if>
                        </a>
                        <c:if test="${hasChildren}">
                            <ul class="dropdown-menu">
                                <c:forEach items="${cate.children}" var="child">
                                    <li><a
                                            <c:if test="${not empty child.url}">href="${child.url}"</c:if>
                                            <c:if test="${empty child.url}">href="${frontPath}/news/list.html?cateId=${child.id}"</c:if>
                                            >${child.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>