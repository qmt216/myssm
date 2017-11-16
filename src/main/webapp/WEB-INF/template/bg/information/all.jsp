<%--
  Created by IntelliJ IDEA.
  User: ibm
  Date: 2016/8/12
  Time: 1:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set value="${pageContext.request.contextPath}" var="frontPath"/>
<form id="pagerForm" action="${frontPath}/bg/information/all.html" method="post">
    <input type="hidden" name="pageNum" value="1"/>
</form>
<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
    <div class="pageHeader">
        <form onsubmit="return navTabSearch(this);" action="${frontPath}/bg/information/all.html" method="post">
            <input type="hidden" name="pageNum" value="1"/>

            <div class="searchBar">
                <table class="searchContent">
                    <tr>
                        <td>
                            名称：
                            <input type="text" name="keyword" value=""/>
                        </td>
                        <td>
                            <div class="buttonActive">
                                <div class="buttonContent">
                                    <button type="submit">查询</button>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </div>
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="edit" href="${frontPath}/bg/information/edit.html?id={sid_obj}&all=1" target="dialog"
                   mask="true" width="900" height="500" resizable='true'><span>修改</span></a></li>
            <li class="line">line</li>
            <li><a class="delete" href="${frontPath}/bg/information/delete.html?id={sid_obj}&all=1"
                   target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
        </ul>
    </div>
    <table class="table" width="99%" layoutH="110">
        <thead>
        <tr>
            <th width="5%">排序</th>
            <th width="30%">标题</th>
            <th width="10%">标题样式</th>
            <th width="10%">分类名称</th>
            <th width="10%">发布人</th>
            <th width="10%">来源</th>
            <th width="10%">链接地址</th>
            <th width="25%">发布时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="bean">
            <tr target="sid_obj" rel="${bean.id}">
                <td>${bean.sort}</td>
                <td><a href="/news/${bean.id}.html" target="_blank">${bean.name}</a></td>
                <td>
                    <c:choose>
                        <c:when test="${bean.nameStyle eq 1}">标红</c:when>
                        <c:when test="${bean.nameStyle eq 2}">加粗</c:when>
                        <c:when test="${bean.nameStyle eq 3}">标红加粗</c:when>
                        <c:otherwise>正常</c:otherwise>
                    </c:choose>
                </td>
                <td>${bean.cateName}</td>
                <td>${bean.author}</td>
                <td>${bean.fr}</td>
                <td>/news/${bean.id}.html</td>
                <td><fmt:formatDate value="${bean.createTime}" type="both" pattern="yyyy-MM-dd hh:mm:ss"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages">
            <span>共${totalCount}条</span>
        </div>
        <div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage}"
             pageNumShown="10" currentPage="${currentPage}"></div>
    </div>
</div>
