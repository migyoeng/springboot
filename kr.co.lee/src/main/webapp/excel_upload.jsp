<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Excel 파일 업로드 + Database에 저장</title>
</head>
<body>

	<form id="frm" method="post" action="./excel_uploadok.do" enctype="multipart/form-data">
		엑셀 파일 : <input type="file" name="mfile" accept=".xlsx, .xls"><br>
		<input type="button" value="전송" onclick="excelok()">
	</form>

</body>

<script>
	function excelok(){
		frm.submit();
	}

</script>
</html>