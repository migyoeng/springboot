<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File I/O Web 여러 개의 File 전송</title>
</head>
<body>
<form id="frm" method="post" action="./fileupok2.do" enctype="multipart/form-data">
	<p>파일 전송</p>
<!-- 
	같은 이름을 여러 개에 부여 시 배열로 저장된다(0,1,2)
	파일 첨부1 : <input type="file" name="mfile"><br>
	파일 첨부2 : <input type="file" name="mfile"><br>
	파일 첨부3 : <input type="file" name="mfile"><br>
-->
	파일 첨부 : <input type="file" name="mfile" onchange="maxfiles(this)" multiple="multiple">첨부파일 최대 3개까지 첨부 가능합니다<br>
	<input type="button" value="전송" onclick="fileup()">
	<input type="button" value="전송2" onclick="fileup2()">
</form>
</body>

<script>

function maxfiles(z){
	var max = 3;
	console.log(z.files);
	if(z.files.length > max){
		alert("첨부파일은 최대 3개까지만 첨부할 수 있습니다");
		frm.mfile.value = "";	//해당 입력된 파일들 초기화 -> 초기화 하지 않으면 파일은 첨부된다
	}
}

function fileup2(){
	frm.submit();
}

function fileup(){
	
	// 4 2 1 : 1번 파일 첨부-> 4, 2번 파일 함께 첨부-> 6, 3번 파일 함께 첨부-> 7 
	var count = 0;	//버튼 클릭 시 함수가 실행 되므로 자동 초기화
	var w = 0;
	//퍼미션 숫자를 응용하여 순서대로 파일이 첨부될 수 있도록 하는 예시
	while(w < 3){
		if(frm.mfile[w].value != ""){
			if(w == 0){
				count = count + 4;
			}
			else if(w == 1){
				count = count + 2;
			}
			else if(w == 2){
				count = count + 1;
			}
		}
		w++;
	}

	if(count == 4 || count == 6 || count == 7){
		frm.submit();
	}
	else {
		alert("파일은 순서에 맞게 첨부하셔야 합니다");
	}
}

</script>
</html>