<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
    <div class="pageContent">

        <form method="post" action="/bg/advPosition/update.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
            <input type="hidden" name="id" value="${bean.id}"/>
            <div class="pageFormContent" layoutH="53">
                <div class="unit">
                    <label>标题：</label>
                    <input type="text" class="required" name="title" value="${bean.name}"/>
                </div>
                <div class="unit">
                    <label>编码：</label>
                    <input type="text" class="required" name="code" value="${bean.code}"/>
                </div>
                <div class="unit">
                    <label>高度：</label>
                    <input type="text" name="height" class="required digits" size="10" value="${bean.height}"/>
                </div>

                <div class="unit">
                    <label>宽度：</label>
                    <input type="text" name="width" class="required" value="${bean.width}"/>
                </div>

                <div class="unit">
                    <label>可用状态：</label>
                    <select name="enable">
                        <option value="1" <c:if test="${bean.enable eq 1}">selected="selected" </c:if>>可用</option>
                        <option value="0" <c:if test="${bean.enable eq 0}">selected="selected" </c:if> >不可用</option>
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