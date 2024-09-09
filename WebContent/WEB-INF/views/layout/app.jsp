<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>マッチングアプリ</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/style.css' />">
        <link rel="stylesheet" href="<c:url value='https://fonts.googleapis.com/css?family=Corben:700 ' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_2">
                    <div class="title"><a href="${pageContext.request.contextPath}">destiny</a></div>
                      <a href="<c:url value='/logout' />"><img src="https://xk3otuup.user.webaccel.jp/wp/wp-content/uploads/2020/09/dating1.png" height="50px" alt=""></a>
                    ${param.header}
                    <a class="message-mark" href="<c:url value='/rooms/index' />">メッセージ一覧</a>
                </div>
            </div>
            <div id="contents2">
            <div id="content">
                ${param.content}
            </div>
            </div>
        </div>
    </body>
</html>