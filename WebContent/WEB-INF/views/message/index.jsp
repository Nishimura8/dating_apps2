<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="header">
        <a class="mypage-mark" href="<c:url value='/mypage/show' />">マイメニュー</a>
        <a class="message-mark" href="<c:url value='/rooms/index' />">マッチングリスト</a>
    </c:param>
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>メッセージ　一覧</h2>
            <c:forEach var="day" items="${day}">
                <div class="day">
                    <c:if test="${ fn:substring( day , 4 , 5 ) == 0}">
                        ${ fn:substring( day , 5 , 6 )}/${ fn:substring( day , 6 , 8 )}(${fn:substring( day , 8 , 9 )})
                    </c:if>
                    <c:if test="${ fn:substring( day , 4 , 5 ) == 1}">
                        ${ fn:substring( day , 4 , 6 )}/${ fn:substring( day , 6 , 8 )}(${fn:substring( day , 8 , 9 )})
                    </c:if>
                </div>
                <c:forEach var="message" items="${message}">
                    <c:if test="${message.posted_day == fn:substring(day, 0, 8)}">
                        <div class="message-list">
                        <img style="width: 39px; height: 52px; border-radius:50%;" src="<c:url value='${message.user.image}'/>">
                            <div class="message-content">
                                <div class="message-content2">
                                    <c:out value="${message.content}"/>
                                    <div class="posted_time">
                                        <c:out value="${message.posted_time}"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </c:forEach>
         <form method="POST" action="<c:url value='/message/create' />">
            <br /><br />
            <div class="c">
            <label for="content">メッセージ</label><br />
            <textarea name="content" rows="10" cols="50"></textarea>
            </div>
            <input type="hidden" name="room_id" value="${room.id}" />
            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">送信</button>
        </form>
    </c:param>
</c:import>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>