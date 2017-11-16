﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page">
    <div class="pageContent">

        <form method="post" action="/bg/adv/insert.html" class="pageForm required-validate"
              enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone);">
            <div class="pageFormContent" layoutH="53">
                <div class="unit">
                    <label>标题：</label>
                    <input type="text" class="required" name="title" size="50"/>
                </div>
                <c:choose>
                    <c:when test="${empty positionId}">
                        <div class="unit">
                            <label>广告位：</label>
                            <select name="positionId">
                                <c:forEach items="${advPositionList}" var="advPosition">
                                    <option value="${advPosition.id}">${advPosition.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="positionId" value="${positionId}"/>
                    </c:otherwise>
                </c:choose>

                <div class="unit">
                    <label>排序：</label>
                    <input type="text" name="sort" class="required digits" size="10"/>
                </div>

                <div class="unit">
                    <label>广告图：</label>
                    <input type="file" name="img" class="required" size="" style="border: none;"/>
                </div>
                <div class="unit">
                    <label>链接地址：</label>
                    <input name="url" class="required url" size="50"/>
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