<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user = "대메뉴 출력!!!!";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
<header>
	${name}(${id})님 환영합니다. 메일 : ${email} <input type="button" value="로그아웃">
</header>

<%@include file="./top.jsp" %>
<%@include file="./notice.jsp" %>
</body>
</html>