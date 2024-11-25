<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기본 문법 5 - function</title>
<%
String user = "정미경,김용태,이선정,이태진";
%>
</head>
<body>
	<cr:set var="product" value="삼성 냉장고 - 삼성 시그니처"/>
	${fn:length(product)} : 데이터 텍스트 길이(개수)<br>
	${fn:replace(product, "삼성", "")} : 해당 데이터 중 설정된 단어를 변경하는 속성<br>
	
	<br><br>
	
	<cr:set var="money" value="123456"></cr:set>
	${fn:substring(money, 0, 4)}<br><!-- 0번 글자부터 4-1번 글자까지 출력 -->
	${fn:substring(money, 1, 2)}<br>
	${fn:substring(money, 4, 6)}<br>
	
	${fn:substringAfter(money, "3")}<br><!-- 3 이후 모든 문자열 출력 -->
	${fn:substringBefore(money, "3")}<br><!-- 3 이전 모든 문자열 출력 -->
	
	<br><br>
	
	<cr:set var="tel" value="${fn:replace('010 1232 7809', ' ', '-')}"></cr:set>
	${tel}
	
	<br><br>
	
	<!-- fn 안에는 JSP 코드를 사용하지 못한다 -->
	<!-- set 태그를 이용하여 jsp, module의 변수값을 핸들링 후 사용 가능하다 -->
	<cr:set var="data" value="<%=user %>"></cr:set>
	<cr:set var="user" value="${fn:split(data, ',')}"></cr:set>
	<cr:forEach var="username" items="${user}">
		${username}
	</cr:forEach>

</body>
</html>