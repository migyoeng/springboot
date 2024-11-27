<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 전송 응용편 + Backend에서 처리하는 방식</title>
</head>
<body>

<form id="frm" method="post" action="./fileupok3.do" enctype="multipart/form-data">
	<div id="box">
		<p>첨부파일 : <input type="file" name="mfile"><input type="button" value="+" onclick="file_plus()"></p>
	</div>
	<button type="button" onclick="filepost()">파일 전송</button>
</form>

</body>
<script src="./file3.js?v=1"></script>
</html>