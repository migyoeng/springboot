<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//jsp 코드 -> jstl로 출력
String user = "홍길동";
//세션 생성
HttpSession hs = request.getSession();
//세션 등록
hs.setAttribute("username", user);
%>
<!-- 
	jstl은 JSP에서 업그레이드 된 View 이며, 단독으로 실행하지 않는다.
	jstl은 엔진 및 함수(코어)가 필요한 부분이 발생한다
	jstl : Jsp Standard Tag Library
-->
 
<!-- jstl 엔진 prefix에서 선언된 이름 : 태그명-->
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- jstl에서 사용하는 함수 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- SQL을 연결하고 싶을 경우-->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSTL 기본 문법 1</title>
</head>
<body>
	<!-- DHTML, XHTML -->
	<input type="text" name="aa" readonly> <!-- DHTML -->
	<input type="text" name="aa" readonly/> <!-- XHTML -->

	<!-- jstl : out 태그 - out.print와 동일 -->
	<cr:out value="값 출력"></cr:out>
	
	<!-- jstl : set 태그 - 값을 이관 받아서 가상의 변수(var)를 받아서 처리 - setAttribute-->
	<cr:set var="a" value="jstl 첫 수업"></cr:set><br>
	<cr:set var="b" value="jstl 첫 수업2222"/>
	${a}
	${b}
	
	<br><br><br>
	<!-- c라는 변수에 user 값을 set -->
	<cr:set var="c" value="<%=user %>"/>
	${c}	<%--jstl 주석 --%>
	
	<br><br><br><br>
	<!-- 세션 초기값 -->
	세션 기본값 : ${username} <!-- session에 저장된 key 값을 ${} 로 출력하면 기본 값이 출려고디는 것을 확인할 수 있다 -->
	
	
	<br><br><br><br>
	<!-- jstl로 session scope를 이용하여 기존 세션 변경하는 방법 -->
	<cr:set var="username" value="jstl" scope="session"/>	
	세션으로 저장된 값을 가져오는 방법 : ${username}	<!-- 기존 세션에서 username 변수에 저장된 값이 jstl로 변경 -->
	
</body>
</html>