<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Controller에서 값을 이관 받은 후 JSTL로 결과 값 출력</title>
</head>
<body>
상품명 : ${pdname}<br><br><br>

<hr>

<cr:forEach var="user" items="${user_list}">
	<cr:if test="${user.get(1) >= 30}">
	${user.get(0)} : ${user.get(1)}<br>
	</cr:if>
</cr:forEach>

<hr>

<cr:forEach var="users" items="${user_data}">
	${users.get(0)} : ${users.get(1)}<br>
</cr:forEach>

<hr>

<cr:forEach var="users" items="${user_data}">
<cr:choose>
<cr:when test="${users.get(1) >= 30}">
	${users.get(0)} : ${users.get(1)}<br>
</cr:when>
</cr:choose>
</cr:forEach>

</body>
</html>