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

        <form method="post" action="/bg/video/insert.html" class="pageForm required-validate"
              enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone);">
            <div class="pageFormContent" layoutH="53">
                <div class="unit">
                    <label>标题：</label>
                    <input type="text" class="required" name="title" size="50"/>
                </div>
                <div class="unit">
                    <label>观看次数：</label>
                    <input type="text" name="view" class="required digits" size="10" value="0"/>
                </div>

                <div class="unit">
                    <label>封面：</label>
                    <input type="file" name="img" class="required" size="" style="border: none;"/>
                </div>
                <div class="unit">
                    <label>视频：</label>
                    <input type="file" name="video" class="required" size="" style="border: none;"/>
                </div>
                <div class="unit">
                    <label>可用状态：</label>
                    <select name="enable">
                        <option value="1">可用</option>
                        <option value="0">不可用</option>
                    </select>
                </div>
                <div class="unit">
                    <label>相关商品链接：</label>
                    <input type="text" name="productUrl" class="required url"/>
                </div>
                <div class="unit">
                    <label>企业介绍：</label>
                    <textarea name="intro" rows="15" cols="100" id="intro"> </textarea>
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