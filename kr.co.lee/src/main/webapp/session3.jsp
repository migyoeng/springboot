<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HttpSession sess = request.getSession();
	//sess.invalidate();	//session에 있는 모든 데이터를 삭제
	sess.removeAttribute("name");	//지정한 가상 변수만 session에서 삭제
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃 된 상황 session3</title>
</head>
<body>

</body>
</html>