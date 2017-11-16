<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<div class="accordion" fillSpace="sideBar" style="height: 100%;">
    <ul class="tree treeFolder">
        <li><a href="javascript:void(0);">功能菜单</a>
            <ul>
                <c:if test="${not empty menu}">
                    <c:forEach items="${menu}" var="menuChild">
                        <li>
                            <a href="${frontPath}${menuChild.url}" target="navTab"  rel="${menuChild.rel}">${menuChild.name}</a>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </li>
    </ul>
</div>