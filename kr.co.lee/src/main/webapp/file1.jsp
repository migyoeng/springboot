<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File I/O Web File 전송</title>
</head>
<!-- 웹 페이지에서 무조건 file 첨부 기능이 들어갈 경우 enctype 필수로 설정!!!!!!! -->
<body>
<form id="frm" method="post" action="./fileupok1.do" enctype="multipart/form-data">
	<p>첨부파일 업로드</p>
	파일 첨부 : <input type="file" name="mfile"><br>
	<input type="button" value="전송" onclick="fileok()"> 
</form>
</body>

<script>
function fileok(){
	frm.submit();
}
</script>
</html>