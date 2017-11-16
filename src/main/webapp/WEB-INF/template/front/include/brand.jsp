<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: ibm
  Date: 2016/8/27
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="section">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">品牌展示</h3>
                    </div>
                    <div class="panel-body">
                        <c:if test="${fn:length(brandList)>0}">
                            <div class="row pingpai">
                                <c:forEach items="${brandList}" var="brand" begin="0" end="5">
                                    <div class="col-xs-2">
                                        <a href="${brand.url}" target="_blank" title="${brand.name}">
                                            <img src="${brand.pic}" class="img-responsive">
                                        </a>
                                    </div>
                                </c:forEach>
                            </div>
                            <c:if test="${fn:length(brandList)>6}">
                                <div class="row pingpai">
                                    <c:forEach items="${brandList}" var="brand" begin="6" end="11">
                                        <div class="col-xs-2">
                                            <a href="${brand.url}" target="_blank" title="${brand.name}">
                                                <img src="${brand.pic}" class="img-responsive">
                                            </a>
                                        </div>
                                    </c:forEach>
                                </div>
                            </c:if>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>