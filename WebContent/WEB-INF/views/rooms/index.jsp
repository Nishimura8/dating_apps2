<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>マッチングアプリ</title>
        <link rel="stylesheet" href="<c:url value='/css/reset.css' />">
        <link rel="stylesheet" href="<c:url value='/css/room.css' />">
        <link rel="stylesheet" href="<c:url value='https://fonts.googleapis.com/css?family=Corben:700 ' />">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div id="header_2">
                    <div class="title"><a href="${pageContext.request.contextPath}/users/index">destiny</a></div>
                        <a href="<c:url value='/logout' />"><img src="https://xk3otuup.user.webaccel.jp/wp/wp-content/uploads/2020/09/dating1.png" height="50px" alt=""></a>
                        <a class="mypage-mark" href="<c:url value='/mypage/show' />">マイメニュー</a>
                        <a class="message-mark" href="<c:url value='/rooms/index' />">メッセージ一覧</a>
                    </div>
                </div>
                <div id="contents2">
                    <div id="content">
                        <c:if test="${flush != null}">
                            <div id="flush_success">
                                <c:out value="${flush}"></c:out>
                            </div>
                        </c:if>
                        <c:forEach var="follow" items="${follows}" varStatus="status">
                            <c:if test="${follow.follower.delete_flg == 0 }">
                                <div class="row${status.count % 2}"></div>
                                <div class="message-information">
                                    <img style="width: 48px; height: 64px" src="<c:url value='${follow.follower.image}' />">
                                    <div class="message_name"><c:out value="${follow.follower.name}" />さんとのメッセージ</div>
                                    <div class="message_action"><a href="<c:url value='/messages/index?id=${follow.room.id}' />">メッセージを見る</a></div>
                                </div>
                           </c:if>
                        </c:forEach>
                        <c:if test="${1 > 3 }">
                            <div id="pagination">
                                <c:forEach var="i" begin="1" end="${((reports_count - 1) / 15) + 1}" step="1">
                                    <c:choose>
                                        <c:when test="${i == page}">
                                             <c:out value="${i}" />&nbsp;
                                        </c:when>
                                        <c:otherwise>
                                            <a href="<c:url value='/rooms/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
                       </c:if>
                    </div>
                </div>
            </div>
    </body>
</html>