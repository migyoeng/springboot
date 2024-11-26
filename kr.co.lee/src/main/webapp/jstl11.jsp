<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!-- 
사용자 : shop
DB : shopping
Table : notice
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL + DB 연결 + 출력</title>
</head>

<sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://192.168.91.3:13306/shopping" user="shop" password="shop1234"/>

<cr:set var="table" value="notice"></cr:set>
<cr:set var="querys" value="order by nidx desc"></cr:set>
<sql:query var="qe" dataSource="${db}">
	select nidx, nsubject, nwriter, ndate from ${table} ${querys}
</sql:query>
<sql:query var="total" dataSource="${db}">
	select count(*) as cnt from ${table} ${querys}
</sql:query>

<!-- sql 태그는 무조건 forEach만 사용 가능하다 -->
<%--
<cr:set var="ctn" value="${total.rows}"></cr:set>
${ctn['ctn']}
 --%>
 
<%-- 전체 갯수를 count한 결과가 출력되지만 좋은 코드가 아니다
<cr:forEach var="ctn" items="${total.rows}">
${ctn['ctn']}
</cr:forEach>
--%>

<body>
<cr:set var="total" value="${total.rows[0].cnt}"/>
현재 총 게시물이 <span id="count"></span>개 있습니다.

<table border="1">
<tr>
	<td>NO</td>
	<td>제목</td>
	<td>글쓴이</td>
	<td>등록일자</td>
</tr>

<!-- rows = table의 컬럼 -->
<cr:forEach var="row" items="${qe.rows}" varStatus="idx">
<!-- 전체 DB 개수를 set 세팅 => js 전달 -->
<tr>
	<%-- <td>${idx.index +1}</td> <!-- 0부터 시작 +1 카운팅 적용 --> --%>
	<td>${total - idx.index}</td>
	<cr:set var="subject" value="${fn:length(row['nsubject'])}"></cr:set>
	<cr:if test="${subject > 10}">
	<cr:set var="jum" value="..."></cr:set>
	</cr:if>
	<cr:if test="${subject < 10}">
	<cr:set var="jum" value=""></cr:set>
	</cr:if>
	<td>${fn:substring(row['nsubject'],0,10)} ${jum}</td>
	<td>${row.nwriter}</td>	<!-- row['컬럼명'], row.컬럼명 -->
	<td>${fn:substring(row['ndate'],0,10)}</td> <!-- 시분초 빼고 날짜만 출력 -->
</tr>
</cr:forEach>
</table>

</body>

<script>
	//JSTL 변수값을 javascript 변수로 이관 후 해당 HTML 태그로 값을 출력 시킨다
	var total = ${total};
	document.getElementById("count").innerText = total;
</script>
</html>