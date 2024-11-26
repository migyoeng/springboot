<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userid = "alrud";
	String username = "정미경";
	HttpSession sess = request.getSession();
	//로그인된 정보를 session으로 생성하는 코드 | 세션 유지 시간을 알아야 한다(web.xml 또는 Application.properties)
	sess.setAttribute("id", userid);
	sess.setAttribute("name", username);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 하는 상황 - session1</title>
</head>
<body>

</body>
</html>