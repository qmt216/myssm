<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    var frontPath="${frontPath}";
    var editor = $('#intro').xheditor({
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

        <form method="post" action="/bg/video/update.html" class="pageForm required-validate"
              enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone);">
            <input type="hidden" name="id" value="${bean.id}"/>
            <div class="pageFormContent" layoutH="53">
                <div class="unit">
                    <label>标题：</label>
                    <input type="text" class="required" name="title" value="${bean.name}"/>
                </div>
                <div class="unit">
                    <label>观看次数：</label>
                    <input type="text" name="view" class="required digits" size="10" value="${bean.view}"/>
                </div>

                <div class="unit">
                    <label>封面：</label>
                    <input type="file" name="img" style="border: none;"/>
                </div>

                <div class="unit">
                    <label>视频：</label>
                    <input type="file" name="video" style="border: none;"/>
                </div>
                <div class="unit">
                    <label>可用状态：</label>
                    <select name="enable">
                        <option value="1" <c:if test="${bean.enable eq 1}">selected="selected" </c:if>>可用</option>
                        <option value="0" <c:if test="${bean.enable eq 0}">selected="selected" </c:if> >不可用</option>
                    </select>
                </div>
                <div class="unit">
                    <label>相关商品链接：</label>
                    <input type="text" name="productUrl" class="required url" value="${bean.productUrl}"/>
                </div>
                <div class="unit">
                    <label>企业介绍：</label>
                    <textarea name="intro" rows="15" cols="100" id="intro">${bean.intro}</textarea>
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