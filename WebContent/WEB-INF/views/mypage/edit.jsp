<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="header">
        <a class="mypage-mark" href="<c:url value='/mypage/show' />">マイメニュー</a>
        <a class="message-mark" href="<c:url value='/rooms/index' />">メッセージ一覧</a>
    </c:param>
    <c:param name="content">
        <form enctype="multipart/form-data"  method="POST" action="<c:url value='/mypage/update' />">
            <c:import url="/WEB-INF/views/mypage/_form.jsp"/>
        </form>

        <p><a href="${pageContext.request.contextPath}">一覧に戻る</a></p>
    </c:param>
</c:import>