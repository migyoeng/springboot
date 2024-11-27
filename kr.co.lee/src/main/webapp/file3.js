var maxfile = 3;	//최대 첨부파일 개수

// + 버튼 클릭 시 작동
function file_plus(){
	maxfile -= 1;
	if(maxfile > 0){
		var html = document.createElement("p");
		html.innerHTML = '첨부파일 : <input type="file" name="mfile">';
		document.getElementById("box").append(html);
	}
	else{
		alert("파일 첨부 기능은 최대 3개까지 사용하실 수 있습니다.");
	}
}

function filepost(){
	frm.submit();
}