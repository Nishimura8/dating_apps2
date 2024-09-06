<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>メッセージ　一覧</h2>
          <c:forEach var="message" items="${message}">
               <div class="message-list">
                <c:out value="${message.content}"/>
                ：
                <c:out value="${message.user.name}"/>
               </div>
         </c:forEach>
         <form method="POST" action="<c:url value='/message/create' />">
            <br /><br />
            <c:out value="氏名:${sessionScope.login_user.name}" />
            <div class="c">
            <label for="content">メッセージ内容</label><br />
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