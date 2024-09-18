<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<c:import url="../layout/app.jsp">
    <c:param name="header">
        <a class="good-list" href="<c:url value='/follow/index' />">いいね一覧</a>
        <a class="mypage-mark" href="<c:url value='/mypage/show' />">マイメニュー</a>
        <a class="message-mark" href="<c:url value='/rooms/index' />">メッセージ一覧</a>
    </c:param>
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <div class="content2">
                <% int i = 0; %>
                <c:forEach var="user" items="${users}">
                    <c:if test= "${user.delete_flg == 0}">
                        <c:if test="${(sessionScope.login_user.gender == 0 && user.gender == 1) || (sessionScope.login_user.gender == 1 && user.gender == 0)}">
                            <div class="user-data">
                                <a href="${pageContext.request.contextPath}/users/show?id=${user.id}">
                                    <img src="<c:url value='${user.image}' />" class="img">
                                </a>
                                <div class="user-information">
                                    <div class="user-name">
                                        <c:out value="${user.name}"/>
                                    </div>
                                    <div class="age">
                                        <c:if test="${user.gender ==1 }">
                                                <% List <String> age = new ArrayList<String>(); %>
                                                <% age = (List<String>)request.getAttribute("womanAgeList"); %>
                                                <%=age.get(i)%>歳
                                                <%i+=1; %>
                                        </c:if>
                                        <c:if test="${user.gender ==0 }">
                                                <% List <String> age = new ArrayList<String>(); %>
                                                <% age = (List<String>)request.getAttribute("manAgeList"); %>
                                                <%=age.get(i)%>歳
                                                <%i+=1; %>
                                        </c:if>
                                    </div>
                                    <div class=likes>
                                        <c:out value="いいね数 ${user.likes}"/>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:if>
                </c:forEach>
        </div>
        <div id="pagination">
            <c:forEach var="i" begin="1" end="${((users_count - 1) / 8) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/users/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>