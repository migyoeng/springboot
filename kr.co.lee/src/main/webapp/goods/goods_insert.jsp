<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
</head>
<body>

<form id="f" method="post" action="${action}">
	상품명 : <input type="text" name="gname"><br>
	상품 가격 : <input type="text" name="gprice"><br>
	할인 가격 : <input type="text" name="gsales"><br>
	<button type="button" onclick="insertok()">상품 등록하기</button>
</form>
</body>

<script>
function insertok(){
	f.submit();
}
</script>
</html>