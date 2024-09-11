<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="header">
        <a class="mypage-mark" href="<c:url value='/users/new' />">マイメニュー</a>
        <a class="message-mark" href="<c:url value='/rooms/index' />">メッセージ一覧</a>
    </c:param>
    <c:param name="content">
        <h2>${user.name}さんのプロフィール</h2>
         <img style="width: 280px; height: 364px" src="<c:url value='${user.image}' />">
        <p>年齢：<c:out value="${user.age}" /></p>
        <p>都道府県：<c:out value="${user.prefecture}" /></p>
        <p>自己紹介文：<c:out value="${user.content}" /></p>
        <form  method="POST" action="<c:url value='/follow/create' />">
            <input type="hidden" name="follower_id" value="${user.id}" />
            <input type="hidden" name="_token" value="${_token}" />
             <c:if test="${follower_count == 0}">
                <input type="submit" value="いいね👍" />
             </c:if>
        </form>
        <p><a href="${pageContext.request.contextPath}">一覧に戻る</a></p>

    </c:param>
</c:import>