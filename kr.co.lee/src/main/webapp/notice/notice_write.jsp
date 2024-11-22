<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 입력파트</title>
</head>
<body>

<form id="f" method="post" action="${action}">
제목 : <input type="text" name="nsubject"><br>
글쓴이 : <input type="text" value="${user}" name="nwriter" readonly="readonly"><br>
패스워드 : <input type="password" name="npass"><br>
내용 : <input type="text" name="ntext"><br>
<button type="button" onclick="writeok()">공지사항 등록</button>
</form>

</body>

<script>
function writeok(){
	//유효성 검사, 세부 조건 추가
	
	f.submit();
}

</script>
</html>