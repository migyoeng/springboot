//ajax로 아이디 체크하는 함수
function idcheck(){
	if(f.mid.value == ""){
		alert("아이디를 입력하셔야 중복 체크를 하실 수 있습니다");
		f.mid.focus();
	}
	else{	//정상적인 아이디 입력 시 ajax로 post 전송
		var data = f.mid.value.replaceAll(" " ,"");
		if(data.length == 0){
			alert("정상적인 아이디를 입력하세요");
		}
		else{
			console.log(f.mid.value);
			var http, result; ///http : 전송하는 값, result : 전달하는 값
			http = new XMLHttpRequest();
			http.onreadystatechange = function(){
				if(http.readyState == 4 && http.status == 200){
					//console.log(this);
					result = this.response;	//Back-end 에서 전달 해주는 값을 담는 역할
					if(result == "0"){
						alert("해당 아이디는 사용할 수 있습니다.");
					}
					else {
						alert("해당 아이디는 사용할 수 없습니다.");
					}
				}
			}
		}
		
		http.open("post", "./idcheck.do", true); //해당 Back-end 경로로 비동기 통신을 사용함
		http.setRequestHeader("content-type", "application/x-www-form-urlencoded") //POST 전용
		http.send("mid=" + f.mid.value);
	}
}

//회원가입 버튼 클릭시 작동되는 함수
function member_check(){	
	var pw = document.getElementById("mpass2");
	var hp1 = document.getElementById("hp1");
	var hp2 = document.getElementById("hp2");
	var hp3 = document.getElementById("hp3");
	
	//submit 일 경우 form 태그에 onsubmit 무조건 사용하며, return/ return false
	if(f.mid.value == ""){
		alert("아이디를 입력하세요");
		return false;
	}
	
	else if(f.mname.value == ""){
		alert("이름을 입력해주세요");
		return false;
	}

	else if(f.mpass.value == ""){
		alert("패스워드를 입력하세요");
		return false;
	}
	
	else if(pw.value == ""){
		alert("패스워드를 한 번 더 확인해주세요");
		return false;
	}
	
	else if(f.telcorp.value == ""){
		alert("통신사를 선택해주세요");
		return false;
	}
	
	else if(f.memail.value.trim() === ""){
		alert("이메일을 입력하셔야 합니다.");
		return false;
	}
	
	else if(f.mpost.value == ""){
		alert("우편 번호를 입력해주세요");
		return false;
	}
	
	else if(f.mroad.value == ""){
		alert("도로명 주소를 입력해주세요");
		return false;
	}
	
	else if(f.maddr.value == ""){
		alert("상세 주소를 입력해주세요");
		return false;
	}
	
	else if(hp1.value == "" || hp2.value == "" || hp3.value == ""){
		alert("연락처를 입력해주세요");
		return false;
	}
	
	else{
		//상세 내역을 체크 확인
		if(f.mpass.value != pw.value){
			alert("동일한 패스워드를 입력하셔야 합니다");
			return false;
		}
		
		if(hp1 == "" || hp2 == "" || hp3 == ""){
			alert("휴대폰 연락처를 입력하셔야 합니다");
			return false;
		}
		
		//세부 조건 생기면 추가
		
		else {	//최종적으로 Guest 모두 입력이 완료되었을 경우 Back-end로 값을 전송
			//휴대폰 번호 정보 조합
			f.mhp.value = hp1.value + "-" + hp2.value + "-" + hp3.value;
			//f.submit();
			//return true;
			return;
		}
	}

}

//카카오 도로명 주소
// 우편번호 찾기 화면을 넣을 element
var element_layer = document.getElementById('layer');

function closeDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
    element_layer.style.display = 'none';
}

//도로명 주소 레이어 팝업
function sample2_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('mpost').value = data.zonecode;
            document.getElementById("mroad").value = addr;

            // iframe을 넣은 element를 안보이게 한다.
            // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
            element_layer.style.display = 'none';
        },
        width : '100%',
        height : '100%',
        maxSuggestItems : 5
    }).embed(element_layer);

    // iframe을 넣은 element를 보이게 한다.
    element_layer.style.display = 'block';

    // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
    initLayerPosition();
}

// 브라우저의 크기 변경에 따라 레이어를 가운데로 이동시키고자 하실때에는
// resize이벤트나, orientationchange이벤트를 이용하여 값이 변경될때마다 아래 함수를 실행 시켜 주시거나,
// 직접 element_layer의 top,left값을 수정해 주시면 됩니다.
function initLayerPosition(){
    var width = 300; //우편번호서비스가 들어갈 element의 width
    var height = 400; //우편번호서비스가 들어갈 element의 height
    var borderWidth = 5; //샘플에서 사용하는 border의 두께

    // 위에서 선언한 값들을 실제 element에 넣는다.
    element_layer.style.width = width + 'px';
    element_layer.style.height = height + 'px';
    element_layer.style.border = borderWidth + 'px solid';
    // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
    element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
    element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
}