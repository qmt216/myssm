<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
    var permission = $("#permission");
    function kkk() {
        var value = "";
        $("#perList ul li").each(function (i, li) {
            if ($(li).find(".ckbox.checked").length > 0) {
                value += "," + $(li).find("a").attr("tvalue");
            }
        });
        if(value!=""){
            value+=","
        }
        permission.val(value);
    }
</script>
<div class="page">
    <div class="pageContent">

        <form method="post" action="/bg/admin/update.html" class="pageForm required-validate"
              enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone);">
            <input type="hidden" name="id" value="${bean.id}"/>

            <div class="pageFormContent" layoutH="53">
                <div class="unit">
                    <label>用户名：${bean.name}</label>
                </div>

                <div class="unit">
                    <label>密码：</label>
                    <input type="text" name="password" value=""/>
                </div>
                <div class="unit">
                    <label>重复密码：</label>
                    <input type="text" name="rePassword" value=""/>
                </div>

                <div class="unit">
                    <label>真实姓名：</label>
                    <input type="text" name="realName" value="${bean.realName}"/>
                </div>
                <div class="unit">
                    <input type="hidden" name="permission" id="permission" value="${bean.permission}"/>
                    <label>权限：</label>
                    <ul class="tree treeFolder treeCheck expand" oncheck="kkk"
                        style="display: inline-block;width: 400px;" id="perList">
                        <li><a>功能节点</a>
                            <ul>
                                <c:forEach items="${menuList}" var="menu">
                                    <li>
                                        <c:set var="menuid" value=",${menu.id},"/>
                                        <a tname="name" tvalue="${menu.id}"
                                           <c:if test="${fn:indexOf(bean.permission,menuid)>=0}">checked="true"</c:if>>
                                                ${menu.name}</a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                    </ul>
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