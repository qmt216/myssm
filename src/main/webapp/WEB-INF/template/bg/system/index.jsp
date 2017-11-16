<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="frontPath"/>
<script type="text/javascript">
    var frontPath="${frontPath}";
    var editor = $('#introduce').xheditor({
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

        <form method="post" action="/bg/system/update.html" class="pageForm required-validate"
              enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone);">
            <input type="hidden" name="id" value="${bean.id}">

            <div class="pageFormContent" layoutH="53">
                <div class="unit">
                    <label>单位名称：</label>
                    <input type="text" class="required" maxlength="100" name="companyName" value="${bean.companyName}"/>
                </div>
                <p/>
                <div class="unit">
                    <label>网站logo(500X100)：</label>
                    <input type="file" maxlength="100" name="logo" style="border: none;"/>
                    <c:if test="${not empty bean.logo}">
                        <img src="${bean.logo}"/>
                    </c:if>
                </div>
                <p/>
                <div class="unit" style="display: none;">
                    <label>联系人：</label>
                    <input type="text" class="required" maxlength="20" name="linkMan" value="${bean.linkMan}"/>
                </div>
                <p/>

                <div class="unit" style="display: none;">
                    <label>在线QQ：</label>
                    <input type="text" name="qq" value="${bean.qq}"/>
                </div>
                <p/>

                <div class="unit" style="display: none;">
                    <label>邮箱：</label>
                    <input type="text" name="email" value="${bean.email}"/>
                </div>
                <p/>

                <div class="unit" style="display: none;">
                    <label>联系电话：</label>
                    <input type="text" name="linkNum" value="${bean.linkNum}"/>
                </div>
                <p/>

                <div class="unit" style="display: none;">
                    <label>地 址：</label>
                    <input type="text" maxlength="20" name="companyAddress" value="${bean.companyAddress}"/>
                </div>
                <p/>

                <div class="unit">
                    <label>单位简介：</label>
                    <textarea name="companySummary" rows="5" cols="100">${bean.companySummary}</textarea>
                </div>
                <p/>

                <div class="unit">
                    <label>单位介绍：</label>
                    <textarea class="editor" name="introduce" rows="15" cols="120" id="introduce">${bean.introduce} </textarea>
                </div>
                <p/>
                <div class="unit">
                    <label>统计代码：</label>
                    <textarea name="staticCode" rows="5" cols="100">${bean.staticCode}</textarea>
                </div>
                <p/>
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
                        <span></span>
                    </li>
                </ul>
            </div>
        </form>
    </div>
</div>
