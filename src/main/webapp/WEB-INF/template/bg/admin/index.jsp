<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form id="pagerForm" action="${frontPath}/bg/admin/index.html" method="post">
    <input type="hidden" name="pageNum" value="1"/>
</form>
<div class="page">
    <div class="pageHeader">
        <form onsubmit="return navTabSearch(this);" action="${frontPath}/bg/admin/index.html" method="post">
            <input type="hidden" name="pageNum" value="1"/>

            <div class="searchBar">
                <table class="searchContent">
                    <tr>
                        <td>
                            用户名：
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
                    <a class="add" href="/bg/admin/add.html" target="navTab" rel="admin_add"><span>添加</span></a>
                </li>
                <li class="line">line</li>
                <li>
                    <a class="edit" href="/bg/admin/edit.html?id={sid_Audit}" target="navTab"
                       warn="请选择一条数据"
                       rel="admin_edit"><span>修改</span></a>
                </li>
                <li class="line">line</li>
                <li>
                    <a class="delete" href="/bg/admin/delete.html?id={sid_Audit}"
                       target="ajaxTodo" title="你确定要删除吗？" warn="请选择一条数据"><span>删除</span></a>
                </li>
            </ul>
        </div>


        <table class="list" width="100%" layoutH="90">
            <thead>
            <tr>
                <th width="15%" style="text-align: center">用户名</th>
                <th width="15%" style="text-align: center">真实姓名</th>
                <th width="30%" style="text-align: center">权限</th>
                <th width="20%" style="text-align: center">最后登陆时间</th>
                <th width="20%" style="text-align: center">创建时间</th>
            </tr>
            </thead>

            <tbody>
            <c:if test="${not empty list}">
                <c:forEach items="${list}" var="item">
                    <tr target="sid_Audit" rel="${item.id}">

                        <td>
                            ${item.name}
                        </td>
                        <td>
                            ${item.realName}
                        </td>
                        <td style="height: 30px;">
                            ---
                        </td>
                        <td style="text-align: center;">
                            <c:if test="${not empty item.lastLoginTime}">
                                ${item.lastLoginTime}
                            </c:if>
                            <c:if test="${empty item.lastLoginTime}">
                                --
                            </c:if>
                        </td>
                        <td style="text-align: center;">
                            <fmt:formatDate value="${item.editTime}" type="both" pattern="yyyy-MM-dd hh:mm:ss"/>
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
