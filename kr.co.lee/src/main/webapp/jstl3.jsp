<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%
//java 배열을 jstl로 출력
String data[] = new String[]{"hong", "kim", "park"};
request.setAttribute("data", data);	//key 가 있는 값 세팅

ArrayList<String> al = new ArrayList<String>();
al.add("홍길동");
al.add("강감찬");
al.add("이순신");
al.add("유관순");

/*응용 문제 :
	해당 배열 값에 맞는 jstl 출력 코드를 작성하시오
	단, 해당 숫자 배열 값 중 2의 배우 및 5의 배수 조건에 맞는 결과 값만 웹에 출력
*/	
Integer number[] = new Integer[]{22,10,19,27,25,33,35,40,2,55,50};
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기본 문법 3 - 배열 + 반복문(foreach)</title>
</head>
<body>
<ol>
<!-- jsp에서 생성된 원시 배열값을 jstl foreach 문을 이용해서 데이터를 출력하는 방식 -->
<cr:forEach var="z" items="<%=data %>">
	<li>${z}</li>
</cr:forEach>
</ol>

<br><br>

<ol>
<!-- begin : 시작 값, end : 종료 값-->
<!-- 기본은 오름차순으로 출력된다. 역순은 출력되지 않는 것을 확인할 수 있다 -->
<cr:forEach var="zz" begin="1" end="5">
	<li>${zz}</li>
</cr:forEach>
</ol>

<ol>
<!-- step : 기본 1 이며 숫자 변경 시 해당 값 + 2씩 출력  -->
<cr:forEach var="zz" begin="1" end="5" step="2">
	<li>${zz}</li>
</cr:forEach>
</ol>

<br><br>

<p>고객 리스트 정보</p>
<!-- ArrayList 배열 값을 받아 forEach 문으로 출력 -->
<cr:forEach var="user" items="<%=al %>">
<span>고객명 : ${user}</span><br>
</cr:forEach>

<!-- 이순신만 출력하기 -->
<!-- ArrayList 배열 값을 받아 forEach 문으로 출력, 조건문에 맞는 값만 출력 -->
<cr:forEach var="user" items="<%=al %>">
<cr:if test="${user == '이순신'}">
<span>고객명 : ${user}</span><br>
</cr:if>
</cr:forEach>

<br><br>

<!-- 응용 문제 -->
<p>2의 배수 및 5의 배수 숫자 출력</p>
<cr:forEach var="num" items="<%=number %>">
<cr:if test="${num%2 == 0 or num%5 == 0}">
	${num}
</cr:if>
</cr:forEach>

<br><br>

<!-- 역순 출력하기 -->
<cr:set var="kno" value="5"></cr:set>
<cr:forEach var="k" begin="0" end="4" step="1">
	<div>${kno-k}</div>
</cr:forEach>


</body>
</html>