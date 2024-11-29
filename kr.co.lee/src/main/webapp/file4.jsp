<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 모바일 페이지 작성 시 사용되는 태그 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>파일 업로드 기능(응용편) - 모바일</title>
</head>
<body>

<form id="frm" method="post" enctype="multipart/form-data">
<!-- hpfile1.do -->
사진 이미지 파일 전송 : <input type="file" name="mfile" accept="image/*" capture="camera"><br>
<input type="button" value="전송" onclick="fileup1()">
<br><br>

<!-- hpfile3.do -->
동영상 파일 전송2 : <input type="file" name="mfile2" accept="video/*" capture="camcorder"><br>
<input type="button" value="전송2" onclick="fileup2()">
<br><br>

<!-- hpfile3.do -->
음성 파일 전송 : <input type="file" name="mfile3" accept="audio/*" capture="microphone"><br>
<input type="button" value="전송3" onclick="fileup3()">
</form>

<script>
	//이미지 파일 전송
	function fileup1(){
		frm.action="./hpfile1.do";
		frm.submit();
	}
	
	//동영상 파일 전송
	function fileup2(){
		frm.action="./hpfile2.do";
		frm.submit();
	}
	
	//음성 파일 전송
	function fileup3(){
		frm.action="./hpfile3.do";
		frm.submit();
	}
</script>

</body>
</html>