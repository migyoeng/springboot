<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>이벤트 참여 리스트 출력 페이지</title>
</head>

<style>
	ul{
		list-style: none;
		margin: 0;
		padding: 0;
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
		width: 100%;
		height: 30px;
	}
	
	ul > li{
		width: 100%;
		line-height: 30px;
		height: 30px;
		border: 1px solid black;
		text-align: center;
	}

</style>

<body>
	<p>[이벤트로 참여한 데이터]</p>
	
	<ul>
		<li>파일명</li>
		<li>참여일</li>
		<li>삭제</li>
	</ul>
	
	<cr:set var="ea" value="${ea}"/>
	<cr:if test="${ea == 0}"></cr:if>
	<ul>
		<li>등록된 게시물이 없습니다.</li>
	</ul>
	<!-- 반복문으로 데이터 출력 시작 -->
	<cr:forEach var="data" items="${all}">
	<ul>
		<li onclick="img_view('${data.mfile_url} ${data.mfile_new}')"><img src="${data.mfile_url} ${data.mfile_new}" style="width: 100%; height: 30px;"></li>
		<li>${fn:substring(data['mfile_date'], 0, 10)}</li>
		<li><input type="button" value="삭제" onclick="data_del('${data.eidx}')"></li>
	</ul>
	</cr:forEach>
	<!-- 반복문으로 데이터 출력 끝 -->
	<input type="button" value="이벤트 참여" onclick="location.href='./file4.jsp'">
	
	<form id="frm">
		<input type="hidden" name="eidx" value="">
	</form>
</body>

<script>
	function img_view(imgurl){
		window.open(imgurl);
	}
	
	function data_del(no){	//atob, btoa
		if(confirm("해당 데이터를 삭제하시겠습니까?")){
			//location.href='./event_del.do?eidx=' + no;
			
			frm.eidx.value = no;
			frm.method="post";
			frm.action="./event_del.do";
			frm.submit();
		}
	}
</script>

</html>