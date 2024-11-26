<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- DB 연결 엔진 -->
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!-- 
사용자 : shop
DB명 : shopping
테이블 : member
바로 데이터베이스 연결이 가능하다
 -->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>JSTL과 SQL 연결</title>
</head>
<body>
<!-- sql JSTL태그 DB 연결하는 정보 -->
<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://192.168.91.3:13306/shopping" user="shop" password="shop1234"/>

<!-- query(DDL) 사용하는 태그 -->
<%-- 첫번째 방법
<sql:query var="ps" sql="select * from member" dataSource="${db}"></sql:query>
 --%>
 <%-- 두번째 방법 --%>
 <cr:set var="table" value="member"></cr:set>
 <cr:set var="where" value="where mid='apink'"></cr:set>
 <cr:set var="order" value="order by midx desc"/>
 
 <sql:query var="ps" dataSource="${db}">
 select * from ${table} ${where} ${order}
 </sql:query>
<!-- query(DDL) 사용하는 태그 끝 -->

<table border="1">
	<tr>
		<td>아이디</td>
		<td>고객명</td>
		<td>이메일</td>
	</tr>
	<!-- JSTL SQL에서 DDL로 실행된 값을 forEach를 통해 웹에 출력하는 형태 -->
	<cr:forEach var="row" items="${ps.rows}">
	<tr>
		<td>${row['mid']}</td>
		<td>${row['mname']}</td>
		<td>${row['memail']}</td>
	</tr>
	</cr:forEach>
</table>


</body>
</html>