function logincheck(){
	if(f.mid.value == ""){
		alert("아이디를 입력하세요");
		return false;
	}
	else if(f.mpass.value == ""){
		alert("패스워드를 입력하세요");
		return false;
	}
	else {
		f.method="post";
		f.action="./loginok.do";
		f.submit();
		//return;
	}
}