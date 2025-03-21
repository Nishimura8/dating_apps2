<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="header">
        <a class="mypage-mark" href="<c:url value='/mypage/show' />">マイメニュー</a>
        <a class="message-mark" href="<c:url value='/rooms/index' />">メッセージ一覧</a>
    </c:param>
    <c:param name="content">
        <h2>あなたのプロフィール</h2>
         <img style="width: 280px; height: 364px" src="<c:url value='${login_user.image}' />">
        <p>年齢：<c:out value="${age}" /></p>
        <p>都道府県：<c:out value="${login_user.prefecture}" /></p>
        <p>自己紹介文：<c:out value="${login_user.content}" /></p>
        <p>いいね数：<c:out value="${login_user.likes}" /></p>
        <form  method="POST" action="<c:url value='/follow/create' />">
            <input type="hidden" name="follower_id" value="${login_user.id}" />
            <input type="hidden" name="_token" value="${_token}" />
        </form>
        <p><a href="<c:url value='/mypage/edit?id=${sessionScope.login_user.id}' />">プロフィールを編集する。</a></p>
        <p><a href="${pageContext.request.contextPath}">一覧に戻る</a></p>
        <p><a href="#" onclick="confirmDestroy();">退会する</a></p>
        <form method="POST" action="<c:url value='/mypage/destroy' />">
             <input type="hidden" name="_token" value="${_token}" />
        </form>
        <script>
            function confirmDestroy() {
                if(confirm("本当に退会してもよろしいでしょうか？")) {
                    document.forms[1].submit();
                }
            }
        </script>

    </c:param>
</c:import>