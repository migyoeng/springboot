<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//아이디, 고객명, 접속 ip, 접속 운영체제, 접속 브라우저 등을 session으로 등록
	HttpSession sess = request.getSession();
	//로그인 해서 등록된 session 정보를 가져오는 기능
	out.print(sess.getAttribute("id"));
	out.print(sess.getAttribute("name"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 후 session2</title>
</head>
<body>

</body>
</html>