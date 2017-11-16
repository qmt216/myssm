<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: qinmingtao
  Date: 2016/8/29
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="pageContent">
    <div class="panelBar">
        <ul class="toolBar">
            <li><a class="add" href="${frontPath}/bg/system/bakSql.html"
                   target="ajaxTodo" title="要现在开始备份数据库嘛？"><span>备份数据库</span></a></li>
            <li class="line">line</li>
            <li><a class="add" href="${frontPath}/bg/system/bakPic.html"
                   target="ajaxTodo" title="要现在开始备份图片数据嘛？"><span>备份图片</span></a></li>
        </ul>
    </div>
    <div class="tabs" currentIndex="0" eventType="click">
        <div class="tabsHeader">
            <div class="tabsHeaderContent">
                <ul>
                    <li><a href="javascript:void(0);"><span>数据库备份</span></a></li>
                    <li><a href="javascript:void(0);"><span>图片备份</span></a></li>
                </ul>
            </div>
        </div>
        <div class="tabsContent" layoutH="60">
            <div>
                <table class="table" width="99%">
                    <thead>
                    <tr>
                        <th width="5%">序号</th>
                        <th width="40%">文件</th>
                        <th width="10%">大小</th>
                        <th width="25%" style="text-align: center;">备份时间</th>
                        <th width="20%" style="text-align: center;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${sqlFileList}" var="bean" varStatus="s">
                        <tr target="sid_obj" <%--rel="${bean.id}"--%>>
                            <td>${s.index+1}</td>
                            <td>${bean.name}</td>
                            <td>${bean.size}</td>
                            <td>${bean.createTime}</td>
                            <td><a href="/bak/${bean.name}">下载</a> </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div>
                <table class="table" width="99%">
                    <thead>
                    <tr>
                        <th width="5%">序号</th>
                        <th width="40%">文件</th>
                        <th width="10%">大小</th>
                        <th width="25%" style="text-align: center;">备份时间</th>
                        <th width="20%" style="text-align: center;">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${uploadFileList}" var="bean" varStatus="s">
                        <tr target="sid_obj" <%--rel="${bean.id}"--%>>
                            <td>${s.index+1}</td>
                            <td>${bean.name}</td>
                            <td>${bean.size}</td>
                            <td>${bean.createTime}</td>
                            <td><a href="/bak/${bean.name}">下载</a> </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
