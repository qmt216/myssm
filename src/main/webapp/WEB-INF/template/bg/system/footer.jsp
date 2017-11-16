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

        <form method="post" action="/bg/system/updateFooter.html" class="pageForm required-validate"
              enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone);">
            <input type="hidden" name="id" value="${bean.id}">
            <div class="pageFormContent" layoutH="53">
                <div class="unit"  style="margin: 50px;">
                    <textarea name="footer" rows="15" cols="120" id="content">${bean.footer}</textarea>
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
                </ul>
            </div>
        </form>
    </div>
</div>