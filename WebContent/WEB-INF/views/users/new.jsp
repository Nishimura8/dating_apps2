<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>新規登録ページ</h2>

        <form enctype="multipart/form-data"  method="POST" action="<c:url value='/users/create' />">
            <c:import url="_form.jsp" />
        </form>

        <p><a href="${pageContext.request.contextPath}">一覧に戻る</a></p>
    </c:param>
</c:import>