<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<c:set value="${pageContext.request.contextPath}" var="frontPath"/>
<div class="page">
	<div class="pageContent">
	
	<form method="post" action="${frontPath}/bg/admin/changePwd.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="id" value="{$id}">
		<div class="pageFormContent" layoutH="58">
			
			<div class="unit">
				<label>旧密码:</label>
				<input type="password" class="required" name="oldpassword">
			</div>
			
			<div class="unit">
				<label>新密码:</label>
				<input type="password" class="required" minlength="6" name="password">
			</div>
			
			<div class="unit">
				<label>确认密码:</label>
				<input type="password" class="required" minlength="6" name="repassword" >
			</div>
		</div>
		
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">修改</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
	</div>
</div>
