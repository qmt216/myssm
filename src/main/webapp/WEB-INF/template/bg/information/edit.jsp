<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="frontPath"/>
<script type="text/javascript">
    var frontPath="${frontPath}";
    var editor = $('#content').xheditor({
        upImgUrl: frontPath + "/bg/information/uploadImg.html",
        upImgExt: "jpg,jpeg,gif,png",
        tools: 'full',
        loadCSS: '<style>pre{margin-left:2em;border-left:3px solid #CCC;padding:0 1em;}</style>',
        onUpload: insertUpload
    });

    function insertUpload(msg) {
    }
</script>
<div class="page">
    <div class="pageContent">

        <form method="post" action="/bg/information/update.html" class="pageForm required-validate"
              enctype="multipart/form-data" onsubmit="return validateCallback(this, dialogAjaxDone);">
            <input type="hidden" name="id" value="${bean.id}"/>
            <div class="pageFormContent" layoutH="53">
                <div class="unit">
                    <label>标题：</label>
                    <input type="text" class="required" name="title" size="50" value="${bean.name}"/>
                    <select name="nameStyle" class="select" style="margin-left: 10px;">
                        <option value="0" <c:if test="${bean.nameStyle eq 0}">selected="selected" </c:if>>正常</option>
                        <option value="1" <c:if test="${bean.nameStyle eq 1}">selected="selected" </c:if>>标红</option>
                        <option value="2" <c:if test="${bean.nameStyle eq 2}">selected="selected" </c:if>>加粗</option>
                        <option value="3" <c:if test="${bean.nameStyle eq 3}">selected="selected" </c:if>>标红加粗</option>
                    </select>
                </div>
                <div class="unit">
                    <label>排序：</label>
                    <input type="text" name="sort" class="required digits" size="10" value="${bean.sort}"/>
                </div>
                <div class="unit">
                    <label>编辑：</label>
                    <input name="author" class="required" size="50" value="${bean.author}"/>
                </div>
                <div class="unit">
                    <label>来源：</label>
                    <input name="fr" class="required" size="50" value="${bean.fr}"/>
                </div>
                <div class="unit">
                    <label>内容：</label>
                    <textarea class="editor" name="content" rows="15" cols="100" id="content">${bean.content}</textarea>
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