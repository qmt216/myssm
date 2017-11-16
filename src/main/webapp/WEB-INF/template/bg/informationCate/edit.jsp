<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
    <div class="pageContent">

        <form method="post" action="/bg/informationCate/update.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
            <input type="hidden" name="id" value="${bean.id}"/>
            <div class="pageFormContent" layoutH="53">
                <div class="unit">
                    <label>父分类：</label>
                    <select name="parentId" readyOnly>
                        <option value="0" <c:if test="${bean.parentId eq 0}">selected</c:if>>顶级分类</option>
                        <c:forEach items="${cateList}" var="cate">
                            <option value="${cate.id}" <c:if test="${bean.parentId eq cate.id}">selected</c:if>>${cate.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="unit">
                    <label>分类名称：</label>
                    <input type="text" class="required" name="name" value="${bean.name}"/>
                </div>
                <div class="unit">
                    <label>链接地址：</label>
                    <input type="text" class="url" name="url" value="${bean.url}"/>
                </div>
                <div class="unit">
                    <label>排序：</label>
                    <input type="text" name="order" class="required digits" size="10" value="${bean.order}"/>
                </div>
                <div class="unit">
                    <label>可用状态：</label>
                    <select name="enable">
                        <option value="0" <c:if test="${bean.enable eq 0}">selected</c:if>>不可用</option>
                        <option value="1" <c:if test="${bean.enable eq 1}">selected</c:if>>可用</option>
                    </select>
                </div>
            </div>
            <div class="formBar">
                <ul>
                    <li>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">保存</button>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="button">
                            <div class="buttonContent">
                                <button type="button" class="close">取消</button>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </form>
    </div>
</div>