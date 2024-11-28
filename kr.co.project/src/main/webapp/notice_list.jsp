<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>
	<p>등록된 게시물 : ${total}</p>
	<table border="1">
		<tr style="text-align:center;">
			<td>번호</td>
			<td>제목</td>
			<td>글쓴이</td>
			<td>등록일</td>
			<td>삭제</td>
		</tr>
		<!-- 반복문 작성 -->
		<cr:forEach var="dbs" items="${all}" varStatus="idx">
		<tr>
			<td>${total - idx.index}</td>
			<td>${dbs.nsubject}</td>
			<td>${dbs.nwriter}</td>
			<td>${fn:substring(dbs.ndate, 0, 10)}</td>
			<td><input type="button" value="삭제" onclick="delete_notice('${dbs.nidx}')"></td>
		</tr>
		</cr:forEach>
	</table>
</body>

<script>
//오류,,잡아,,,
function delete_notice(nidx){
	var key = window.btoa("kr.co.project");
	if(confirm("해당 게시물을 삭제하시겠습니까?\n삭제된 데이터는 복구하지 못합니다.")){
		location.href="./notice_delete.do?nidx=" + window.btoa(nidx) + "&key=" key;
	}
}

</script>
</html>