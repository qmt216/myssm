﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
    <div class="pageContent">

        <form method="post" action="/bg/download/insert.html" class="pageForm required-validate"
              enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone);">
            <div class="pageFormContent" layoutH="53">
                <div class="unit">
                    <label>标题：</label>
                    <input type="text" class="required" name="title" size="50"/>
                </div>
                <div class="unit">
                    <label>排序：</label>
                    <input type="text" name="sort" class="required digits" size="10" value="99"/>
                </div>

                <div class="unit">
                    <label>文件：</label>
                    <input type="file" name="img" class="required" size="" style="border: none;"/>
                </div>
                <div class="unit">
                    <label>下载是否需要登陆：</label>
                    <select name="isLogin">
                        <option value="0" <c:if test="${bean.isLogin eq 0}">selected="selected" </c:if> >否</option>
                        <option value="1" <c:if test="${bean.isLogin eq 1}">selected="selected" </c:if>>是</option>
                    </select>
                </div>
                <div class="unit">
                    <label>可用状态：</label>
                    <select name="enable">
                        <option value="1">可用</option>
                        <option value="0">不可用</option>
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