<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <div class="pageContent">
        <div class="panelBar">
            <ul class="toolBar">
                <li>
                    <a class="add" href="/bg/advPosition/add.html" target="dialog"
                       resizable='false' maxable='false' minable='true' mask="true"
                       mask="true" width="540" height="300"><span>添加</span></a>
                </li>
                <li class="line">line</li>
                <li>
                    <a class="edit" href="/bg/advPosition/edit.html?id={sid_Audit}" target="dialog"
                       resizable='false' maxable='false' minable='true' mask="true"
                       mask="true" width="540" height="300" warn="请选择一条数据" id="edit"><span>修改</span></a>
                </li>
                <%--<li class="line">line</li>
                <li>
                    <a class="delete" href="/bg/advPosition/delete.html?id={sid_Audit}"
                       target="ajaxTodo" title="你确定要删除吗？" warn="请选择一条数据"><span>删除</span></a>
                </li>--%>
            </ul>
        </div>


        <table class="list" width="100%" layoutH="90">
            <thead>
            <tr>
                <th width="30%" style="text-align: center">标题</th>
                <th width="20%" style="text-align: center">编码</th>
                <th width="15%" style="text-align: center">高度</th>
                <th width="15%" style="text-align: center">宽度</th>
                <th width="20%" style="text-align: center">可用状态</th>
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
                                ${item.code}
                        </td>
                        <td>
                                ${item.height}
                        </td>
                        <td>
                                ${item.width}
                        </td>
                        <td style="text-align: center;">
                            <c:choose>
                                <c:when test="${item.enable eq 1}">
                                    可用
                                </c:when>
                                <c:otherwise>
                                    <span style="color:red">不可用</span>
                                </c:otherwise>
                            </c:choose>
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
