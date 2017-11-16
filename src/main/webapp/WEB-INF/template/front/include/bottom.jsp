<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
    .bottom-link a {
        color: #ffffff;
    }

    .bottom-text p {
        color: #ffffff;
    }
</style>
<footer class="section section-primary">
    <div class="container">
        <c:if test="${not empty links}">
            <div class="row">
                <div class="col-sm-12 bottom-link" style="text-align: center;">
                    <c:forEach items="${links}" var="friend" varStatus="s">
                        <a href="${friend.url}" title="${friend.name}" target="_blank">${friend.name}</a>
                        <c:if test="${!s.last}">|</c:if>
                    </c:forEach>
                </div>
            </div>
        </c:if>
        <div class="row" style="margin-top: 20px;">
            <div class="col-sm-12 bottom-text">
                ${system.footer}
            </div>
        </div>
    </div>
</footer>