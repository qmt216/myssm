<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
    ul.rightTools {
        float: right;
        display: block;
    }

    ul.rightTools li {
        float: left;
        display: block;
        margin-left: 5px
    }
</style>

<div class="pageContent">
    <div class="tabs">
        <div class="tabsContent">
            <div>

                <div layoutH="12" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
                    <ul class="tree treeFolder">
                        <c:if test="${not empty headCates}">
                            <c:forEach items="${headCates}" var="headCate">
                                <li>
                                    <c:set value="${not empty headCate.children}" var="hasChildren"/>
                                    <a href="/bg/information/list.html?cateId=${headCate.id}" <c:if test="${!hasChildren}">target="ajax" rel="jbsxBox"</c:if> >${headCate.name}</a>
                                    <c:if test="${hasChildren}">
                                    <ul>
                                        <c:forEach items="${headCate.children}" var="cate">
                                        <li><a href="/bg/information/list.html?cateId=${cate.id}" target="ajax" rel="jbsxBox">${cate.name}</a></li>
                                        </c:forEach>
                                    </ul>
                                    </c:if>
                                </li>
                            </c:forEach>
                        </c:if>
                    </ul>
                </div>

                <div id="jbsxBox" class="unitBox" style="margin-left:246px;">

                </div>

            </div>
        </div>
        <div class="tabsFooter">
            <div class="tabsFooterContent"></div>
        </div>
    </div>

</div>