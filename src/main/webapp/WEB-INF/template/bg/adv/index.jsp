<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form id="pagerForm" action="${frontPath}/bg/adv/index.html" method="post">
    <input type="hidden" name="pageNum" value="1"/>
</form>
<div class="page">
    <div class="pageHeader">
        <form onsubmit="return navTabSearch(this);" action="${frontPath}/bg/adv/index.html" method="post">
            <input type="hidden" name="pageNum" value="1"/>

            <div class="searchBar">
                <table class="searchContent">
                    <tr>
                        <td>
                            广告名称：
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

    <div class="pageContent">
        <div class="panelBar">
            <ul class="toolBar">
                <li>
                    <a class="add" href="/bg/adv/add.html<c:if test="${not empty positionId}">?positionId=${positionId}</c:if>" target="navTab" id="adv_add_${positionId}"><span>添加</span></a>
                </li>
                <li class="line">line</li>
                <li>
                    <a class="edit" href="/bg/adv/edit.html?id={sid_Audit}<c:if test="${not empty positionId}">&positionId=${positionId}</c:if>" target="navTab"
                       warn="请选择一条数据" id="adv_edit" rel="adv_edit_${positionId}"><span>修改</span></a>
                </li>
                <li class="line">line</li>
                <li>
                    <a class="delete" href="/bg/adv/delete.html?id={sid_Audit}"
                       target="ajaxTodo" title="你确定要删除吗？" warn="请选择一条数据"><span>删除</span></a>
                </li>
            </ul>
        </div>


        <table class="list" width="100%" layoutH="90">
            <thead>
            <tr>
                <th width="5%" style="text-align: center">排序</th>
                <th width="30%" style="text-align: center">标题</th>
                <th width="10%" style="text-align: center">图片</th>
                <th width="35%" style="text-align: center">链接地址</th>
                <th width="10%" style="text-align: center">是否可用</th>
                <th width="10%" style="text-align: center">创建时间</th>
            </tr>
            </thead>


            <tbody>
            <c:if test="${not empty list}">
                <c:forEach items="${list}" var="adv">
                    <tr target="sid_Audit" rel="${adv.id}">

                        <td>
                            <c:if test="${not empty adv.sort}">
                                ${adv.sort}
                            </c:if>
                            <c:if test="${empty adv.sort}">
                                0
                            </c:if>
                        </td>
                        <td>
                                ${adv.name}
                        </td>
                        <td style="height: 60px;margin: 0 auto; vertical-align: middle;text-align: center;">
                            <c:if test="${not empty adv.pic}">
                                <img src="${adv.pic}" style="max-height: 60px;max-width: 60px;">
                            </c:if>
                            <c:if test="${empty adv.pic}">
                                <img src="/upload/nopic_60.gif" style="max-height: 60px;max-width: 60px;">
                            </c:if>
                        </td>
                        <td>
                             <a href="${adv.url}">${adv.url}</a>
                        </td>
                        <td style="text-align: center;">
                            <c:choose>
                                <c:when test="${adv.enable eq 1}">
                                    可用
                                </c:when>
                                <c:otherwise>
                                    <span style="color:red">不可用</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: center;">
                            <fmt:formatDate value="${adv.createTime}" pattern="yyyy-MM-dd hh:mm:ss" type="both"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
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

</div>
